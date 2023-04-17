package pe.isil.resource;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.model.Categoria;
import pe.isil.service.CategoriaService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CategoriaResource {
    //Importar el servicio
    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    //Api para listar todas las categorias
    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getAll() {
    //Hago la consulta al service para traer el listado completo y lo guardo en una lista tipo categoria
    List<Categoria> categorias = categoriaService.getAll();

        if(categorias.isEmpty()){
            //Si no hay datos retorna Status No content
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    //Si hay datos retorna la lista y status ok
    return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    //Api para guardar categoria
    @PostMapping("/categorias")
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria){
        categoriaService.saveCategoria(categoria);
        return new ResponseEntity<>(categoria, HttpStatus.CREATED);
    }

    //Api buscar por id una categoria
    @GetMapping("categorias/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Integer id){
        Categoria categoriaActual = categoriaService.findById(id);
            if (categoriaActual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
       return new ResponseEntity<>(categoriaActual, HttpStatus.OK);
    }

    //Api Editar una Categoria
    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> update(@RequestBody Categoria categoria, @PathVariable Integer id) {
        Categoria categoriaActual = categoriaService.findById(id);
        if (categoriaActual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoria.setId(id); //En duda si se puede mejorar está parte
        categoriaService.updateCategoria(categoria);

        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    //Api eliminar una categoria
    @DeleteMapping("categorias/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Categoria categoriaActual = categoriaService.findById(id);
        if(categoriaActual==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoriaService.removeCategoria(categoriaActual);
        return new ResponseEntity(HttpStatus.OK);
    }

}
