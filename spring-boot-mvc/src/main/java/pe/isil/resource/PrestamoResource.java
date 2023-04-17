package pe.isil.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.model.Libro;
import pe.isil.model.Prestamo;
import pe.isil.service.PrestamoService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class PrestamoResource {

    //Importar el servicio
    private final PrestamoService prestamoService;

    public PrestamoResource(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    //Api para listar todos los prestamos
    @GetMapping("/prestamos")
    public ResponseEntity<List<Prestamo>> getAll(){
        //Hago la consulta al service para traer el listado completo y lo guardo en una lista tipo Prestamo
        List<Prestamo> prestamos = prestamoService.getAll();

        if(prestamos.isEmpty()){
            //Si no hay datos retorna Status No content
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //Si hay datos retorna la lista y status ok
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }

    //Api para guardar un Prestamo
    @PostMapping("/prestamos")
    public ResponseEntity<Prestamo> save(@RequestBody Prestamo prestamo){
        prestamoService.savePrestamo(prestamo);
        return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
    }

    //Api buscar por id una categoria
    @GetMapping("/prestamos/{id}")
    public ResponseEntity<Prestamo> getById(@PathVariable Integer id){
        Prestamo prestamoActual = prestamoService.findById(id);
        if (prestamoActual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(prestamoActual, HttpStatus.OK);
    }

    //Api para editar un libro
    @PutMapping("/prestamos/{id}")
    public ResponseEntity<Prestamo> update(@RequestBody Prestamo prestamo, @PathVariable Integer id) {
        Prestamo prestamoActual = prestamoService.findById(id);
        if (prestamoActual == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        prestamo.setId(id); //En duda si se puede mejorar está parte
        prestamoService.updatePrestamo(prestamo);

        return new ResponseEntity<>(prestamo, HttpStatus.OK);
    }

    //Api para eliminar un Prestamo
    @DeleteMapping("prestamos/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Prestamo prestamoActual = prestamoService.findById(id);
        if(prestamoActual==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        prestamoService.removePrestamo(prestamoActual);
        return new ResponseEntity(HttpStatus.OK);
    }
}
