package pe.isil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.model.Categoria;
import pe.isil.model.Libreria;
import pe.isil.model.Libro;
import pe.isil.service.CategoriaService;
import pe.isil.service.LibreriaService;
import pe.isil.service.LibroService;

import java.util.List;

@Controller
public class LibroController {
    private final LibroService libroService;
    private final LibreriaService libreriaService;

    public LibroController(LibroService libroService, LibreriaService libreriaService, CategoriaService categoriaService) {
        this.libroService = libroService;
        this.libreriaService = libreriaService;
        this.categoriaService = categoriaService;
    }

    private final CategoriaService categoriaService;

    //Servicio Listar Libros
    @GetMapping("/libros")
    public String getLibrosList(Model model){
        List<Libro> libros = libroService.getAll();
        model.addAttribute("libros", libros);

        return "libro";
    }

    //Servicio obtener nuevo un nuevo libro
    @GetMapping("libros/add")
    public String addLibro(Model model){

        //Me traigo la lista de categorias
        List<Categoria> listaCategorias = categoriaService.getAll();
        //Me traigo la lista de Sedes Libreria
        List<Libreria> listaLibrerias = libreriaService.getAll();

        //Le enviamos a la vista esos datos, la lista de categorias
        model.addAttribute("listaCategorias", listaCategorias);

        //Le enviamos a la vista esos datos, la lista de librerias
        model.addAttribute("listaLibrerias", listaLibrerias);

        model.addAttribute("libro", new Libro());
        return "libro-add";
    }

    //Servicio para guardar un nuevo Libro
    @PostMapping("/libros/save")
    public String saveLibro(Libro libro, Model model){
        libroService.saveLibro(libro);

        return "redirect:/libros";
    }

    //Servicio para obtener un libro a editar por id
    @GetMapping("/libros/edit/{id}")
    public String libroEdit(@PathVariable Integer id, Model model){

        //Me traigo la lista de categorias
        List<Categoria> listaCategorias = categoriaService.getAll();

        //Me traigo la lista de Sedes Libreria
        List<Libreria> listaLibrerias = libreriaService.getAll();

        //Le enviamos a la vista esos datos, la lista de categorias
        model.addAttribute("listaCategorias", listaCategorias);

        //Le enviamos a la vista esos datos, la lista de librerias
        model.addAttribute("listaLibrerias", listaLibrerias);

        Libro libroActual = libroService.findById(id);
        model.addAttribute("libroActual", libroActual);

        return "libro-edit";
    }


    //Servicio para actualizar un libro
    @PostMapping("/libros/update")
    public String libroUpdate(Libro libro){
        libroService.updateLibro(libro);

        return "redirect:/libros";
    }

    //Servicio para eliminar un Libro
    @GetMapping("/libros/delete/{id}")
    public String libroDelete(@PathVariable Integer id){
        Libro libroActual = libroService.findById(id);
        if(libroActual!=null){
            libroService.removeLibro(libroActual);
        }
        return "redirect:/libros";
    }

    //Crear un Servicio que permita buscar por isbn
    /*
    @PostMapping("libros/save")
    public String saveLibro(Libro libro, Model model) {
        return null;
    }
     */

}
