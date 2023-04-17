package pe.isil.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.model.Categoria;
import pe.isil.model.Usuario;
import pe.isil.service.UsuarioService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class UsuarioResource {
    //Importar el servicio
    private final UsuarioService usuarioService;

    public UsuarioResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Api para listar todos los usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAll() {
        //Hago la consulta al service para traer el listado completo y lo guardo en una lista tipo usuario
        List<Usuario> usuarios = usuarioService.getAll();

        if(usuarios.isEmpty()){
            //Si no hay datos retorna Status No content
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //Si hay datos retorna la lista y status ok
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    //Api para guardar un usuario
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        usuarioService.saveUsuario(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    //Api buscar por id un usuario
    @GetMapping("usuarios/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id){
        Usuario usuarioActual = usuarioService.findById(id);
        if (usuarioActual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioActual, HttpStatus.OK);
    }
    //Api Editar un usuario
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @PathVariable Integer id) {
        Usuario usuarioActual = usuarioService.findById(id);
        if (usuarioActual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuario.setId(id); //En duda si se puede mejorar está parte
        usuarioService.updateUsuario(usuario);

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    //Api eliminar un usuario
    @DeleteMapping("usuarios/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Usuario usuarioActual = usuarioService.findById(id);
        if(usuarioActual==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioService.removeUsuario(usuarioActual);
        return new ResponseEntity(HttpStatus.OK);
    }
}
