package pe.isil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.model.Categoria;
import pe.isil.model.Libreria;
import pe.isil.model.Libro;
import pe.isil.service.LibreriaService;

import java.util.List;

@Controller
public class LibreriaController {

    private final LibreriaService libreriaService;

    public LibreriaController(LibreriaService libreriaService) {
        this.libreriaService = libreriaService;
    }

    //Servicio Listar librerias
    @GetMapping("/librerias")
    public String getLibrosList(Model model){
        List<Libreria> librerias = libreriaService.getAll();
        model.addAttribute("librerias", librerias);

        return "libreria";
    }

    //Servicio obtener nuevo un nuevo librerias
    @GetMapping("librerias/add")
    public String addLibreria(Model model){

        model.addAttribute("libreria", new Libreria());
        return "libreria-add";
    }

    //Servicio para guardar un nuevo Libro
    @PostMapping("/librerias/save")
    public String saveLibreria(Libreria libreria, Model model){
        libreriaService.saveLibreria(libreria);

        return "redirect:/librerias";
    }

    //Servicio para obtener un libreria a editar por id
    @GetMapping("/librerias/edit/{id}")
        public String libreriaEdit(@PathVariable Integer id, Model model){
        Libreria libreriaActual = libreriaService.findById(id);

        model.addAttribute("libreriaActual", libreriaActual);

        return "libreria-edit";
    }


    //Servicio para actualizar un libro
    @PostMapping("/librerias/update")
    public String libreriaUpdate(Libreria libreria){
        libreriaService.updateLibreria(libreria);

        return "redirect:/librerias";
    }

    //Servicio para eliminar un Libro
    @GetMapping("/librerias/delete/{id}")
    public String libreriasDelete(@PathVariable Integer id){
        Libreria libreriaActual = libreriaService.findById(id);
        if(libreriaActual!=null){
            libreriaService.removeLibreria(libreriaActual);
        }
        return "redirect:/librerias";
    }


}
