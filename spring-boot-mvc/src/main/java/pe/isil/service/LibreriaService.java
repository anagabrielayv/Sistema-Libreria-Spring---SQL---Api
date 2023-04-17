package pe.isil.service;

import org.springframework.stereotype.Service;
import pe.isil.model.Libreria;
import pe.isil.repository.LibreriaRepository;

import java.util.List;

@Service
public class LibreriaService {


    private final LibreriaRepository libreriaRepository;

    public LibreriaService(LibreriaRepository libreriaRepository) {
        this.libreriaRepository = libreriaRepository;
    }


    //Listar Libreria
    public List<Libreria> getAll(){
        return libreriaRepository.findAll();
    }

    //Guardar un nuevo Libreria
    public void saveLibreria(Libreria libreria){
        libreriaRepository.save(libreria);
    }

    //Eliminar un Libreria
    public void removeLibreria(Libreria libreria){
        libreriaRepository.delete(libreria);
    }

    //Actualizar un Libreria
    public void updateLibreria(Libreria libreria){
        libreriaRepository.save(libreria);
    }



    //Buscar un Libreria por su ID
    public Libreria findById(Integer id){
        return libreriaRepository.findById(id).orElse(null);
    }
}
