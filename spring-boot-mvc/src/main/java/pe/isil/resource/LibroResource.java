package pe.isil.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.model.Categoria;
import pe.isil.model.Libro;
import pe.isil.service.CategoriaService;
import pe.isil.service.LibroService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class LibroResource {

    //Importar el servicio
    private final LibroService libroService;
    private final CategoriaService categoriaService;

    public LibroResource(LibroService libroService, CategoriaService categoriaService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
    }

    //Api para listar todos los libros
    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> getAll(){
        //Hago la consulta al service para traer el listado completo y lo guardo en una lista tipo libro
        List<Libro> libros = libroService.getAll();

        if(libros.isEmpty()){
            //Si no hay datos retorna Status No content
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //Si hay datos retorna la lista y status ok
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    //Api para guardar un libro
    @PostMapping("/libros")
    public ResponseEntity<Libro> save(@RequestBody Libro libro){
        libroService.saveLibro(libro);
        return new ResponseEntity<>(libro, HttpStatus.CREATED);
    }

    //Api buscar por id una categoria
    @GetMapping("/libros/{id}")
    public ResponseEntity<Libro> getById(@PathVariable Integer id){
        Libro libroActual = libroService.findById(id);
        if (libroActual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(libroActual, HttpStatus.OK);
    }



    //Api para editar un libro
    @PutMapping("/libros/{id}")
    public ResponseEntity<Libro> update(@RequestBody Libro libro, @PathVariable Integer id) {
        Libro libroActual = libroService.findById(id);
        if (libroActual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        libro.setId(id); //En duda si se puede mejorar está parte
        libroService.updateLibro(libro);

        return new ResponseEntity<>(libro, HttpStatus.OK);
    }

    //Api para eliminar un Libro
    @DeleteMapping("libros/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Libro libroActual = libroService.findById(id);
        if(libroActual==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        libroService.removeLibro(libroActual);
        return new ResponseEntity(HttpStatus.OK);
    }
}
