package pe.isil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.isil.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
