package pe.isil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.isil.model.Libro;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    //Optional<Libro> findByIsbn(String isbn);
}
