package pe.isil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.model.Categoria;
import pe.isil.service.CategoriaService;

import java.util.List;

@Controller
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    //Servicio Listar categorias
    @GetMapping("/categorias")
    public String getCategoriasLista(Model model) {
        List<Categoria> categorias =categoriaService.getAll();
        model.addAttribute("categorias", categorias);

        return "categoria";
    }
    //Servicio obtener la categoria que se va agregar
    @GetMapping("/categorias/add")
    public String addCategoria(Model model) {
        //necesitamos pasarle una nueva instancia de objeto categoria
        model.addAttribute("categoria", new Categoria());

        return "categoria-add";
    }

    //Servicio para guardar una categoria nueva, necesitamos pasarle el objeto categoria con los nuevos datos
    @PostMapping("/categorias/save")
    public String saveCategoria(Categoria categoria, Model model){
        categoriaService.saveCategoria(categoria);

        return "redirect:/categorias";
    }

    //Servicio para obtener una categoria por id
    @GetMapping("/categorias/edit/{id}")
    public String categoriaEdit(@PathVariable Integer id, Model model){
        Categoria categoriaActual = categoriaService.findById(id);
        model.addAttribute("categoriaActual", categoriaActual);

        return "categoria-edit";
    }

    //Servicio para actualizar una categoria
    @PostMapping("/categorias/update")
    public String categoriaUpdate(Categoria categoria){
        categoriaService.updateCategoria(categoria);

        return "redirect:/categorias";
    }

    //Servicio para eliminar una categoria
    @GetMapping("/categorias/delete/{id}")
    public String categoriaDelete(@PathVariable Integer id){
        Categoria categoriaActual = categoriaService.findById(id);
        if(categoriaActual!=null){
            categoriaService.removeCategoria(categoriaActual);
        }
    return "redirect:/categorias";
    }




}
