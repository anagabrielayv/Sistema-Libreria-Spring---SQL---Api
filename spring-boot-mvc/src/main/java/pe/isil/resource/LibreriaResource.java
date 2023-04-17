package pe.isil.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.model.Libreria;
import pe.isil.service.LibreriaService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class LibreriaResource {

    //Importar el servicio

    private final LibreriaService libreriaService;

    public LibreriaResource(LibreriaService libreriaService) {
        this.libreriaService = libreriaService;
    }


    //Api para listar todos los libros
    @GetMapping("/librerias")
    public ResponseEntity<List<Libreria>> getAll(){
        //Hago la consulta al service para traer el listado completo y lo guardo en una lista tipo libreria
        List<Libreria> librerias = libreriaService.getAll();

        if(librerias.isEmpty()){
            //Si no hay datos retorna Status No content
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //Si hay datos retorna la lista y status ok
        return new ResponseEntity<>(librerias, HttpStatus.OK);
    }

    //Api para guardar un libreria
    @PostMapping("/librerias")
    public ResponseEntity<Libreria> save(@RequestBody Libreria libreria){
        libreriaService.saveLibreria(libreria);
        return new ResponseEntity<>(libreria, HttpStatus.CREATED);
    }


    //Api buscar por id una libreria
    @GetMapping("/librerias/{id}")
    public ResponseEntity<Libreria> getById(@PathVariable Integer id){
        Libreria libreriaActual = libreriaService.findById(id);
        if (libreriaActual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(libreriaActual, HttpStatus.OK);
    }

    //Api para editar un libro
    @PutMapping("/librerias/{id}")
    public ResponseEntity<Libreria> update(@RequestBody Libreria libreria, @PathVariable Integer id) {
        Libreria libreriaActual = libreriaService.findById(id);
        if (libreriaActual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        libreria.setId(id); //En duda si se puede mejorar está parte
        libreriaService.updateLibreria(libreria);

        return new ResponseEntity<>(libreria, HttpStatus.OK);
    }

    //Api para eliminar una libreria
    @DeleteMapping("librerias/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Libreria libreriaActual = libreriaService.findById(id);
        if(libreriaActual==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        libreriaService.removeLibreria(libreriaActual);
        return new ResponseEntity(HttpStatus.OK);
    }

}
