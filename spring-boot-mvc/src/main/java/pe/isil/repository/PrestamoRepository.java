package pe.isil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.isil.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
}
