package pe.isil.service;

import org.springframework.stereotype.Service;
import pe.isil.model.Categoria;
import pe.isil.repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    //Listar Categorias
    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    //Guardar una Categoria, necesitamos pasarle un objeto (categoria)
    public void saveCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }

    //Eliminar una Categoria, necesitamos pasarle un objeto (categoria)
    public void removeCategoria(Categoria categoria){
        categoriaRepository.delete(categoria);
    }

    //Actualizar una categoria, necesitamos pasarle un objeto (categoria)
    public void updateCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }

    //Buscar una categoria por id, necesitamos pasarle el id cómo parámetro
    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }
}
