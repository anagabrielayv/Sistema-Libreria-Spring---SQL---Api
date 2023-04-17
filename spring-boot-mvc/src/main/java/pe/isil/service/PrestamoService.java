package pe.isil.service;

import org.springframework.stereotype.Service;
import pe.isil.model.Libro;
import pe.isil.model.Prestamo;
import pe.isil.repository.PrestamoRepository;

import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    //Listar Prestamos
    public List<Prestamo> getAll(){
        return prestamoRepository.findAll();
    }

    //Guardar una nueva reserva de Prestamo
    public void savePrestamo(Prestamo prestamo){
        prestamoRepository.save(prestamo);
    }

    //Eliminar un Prestamo
    public void removePrestamo(Prestamo prestamo){
        prestamoRepository.delete(prestamo);
    }

    //Actualizar un Prestamo
    public void updatePrestamo(Prestamo prestamo){
        prestamoRepository.save(prestamo);
    }


    //Buscar un Prestamo por su ID
    public Prestamo findById(Integer id){
        return prestamoRepository.findById(id).orElse(null);
    }
}

