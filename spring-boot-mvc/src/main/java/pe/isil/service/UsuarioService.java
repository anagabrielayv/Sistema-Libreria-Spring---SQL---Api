package pe.isil.service;

import org.springframework.stereotype.Service;
import pe.isil.model.Categoria;
import pe.isil.model.Usuario;
import pe.isil.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Listar Usuarios
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    //Guardar un usuario, necesitamos pasarle un objeto (usuario)
    public void saveUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    //Eliminar un Usuario, necesitamos pasarle un objeto (usuario)
    public void removeUsuario(Usuario usuario){
        usuarioRepository.delete(usuario);
    }

    //Actualizar un usuario, necesitamos pasarle un objeto (usuario)
    public void updateUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    //Buscar un usuario por id, necesitamos pasarle el id cómo parámetro, cambiar necesitamos la busqueda por DNi o documento
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
