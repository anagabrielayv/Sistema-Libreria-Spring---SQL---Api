package pe.isil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.isil.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
