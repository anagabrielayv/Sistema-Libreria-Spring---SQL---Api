package pe.isil.service;

import org.springframework.stereotype.Service;
import pe.isil.model.Libro;
import pe.isil.repository.LibroRepository;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    //Listar Libros
    public List<Libro> getAll(){
        return libroRepository.findAll();
    }

    //Guardar un nuevo Libro
    public void saveLibro(Libro libro){
        libroRepository.save(libro);
    }

    //Eliminar un Libro
    public void removeLibro(Libro libro){
        libroRepository.delete(libro);
    }

    //Actualizar un Libro
    public void updateLibro(Libro libro){
        libroRepository.save(libro);
    }

    //Faltar crear Buscar un Libro por su ISBN
    //Codigo

    //Buscar un Libro por su ID
    public Libro findById(Integer id){
        return libroRepository.findById(id).orElse(null);
    }
}
