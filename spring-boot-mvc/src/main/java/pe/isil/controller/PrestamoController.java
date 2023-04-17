package pe.isil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.model.*;
import pe.isil.service.LibroService;
import pe.isil.service.PrestamoService;
import pe.isil.service.UsuarioService;

import java.util.List;

@Controller
public class PrestamoController {

    private final PrestamoService prestamoService;

    private final UsuarioService usuarioService;
    private final LibroService libroService;

    public PrestamoController(PrestamoService prestamoService, UsuarioService usuarioService, LibroService libroService) {
        this.prestamoService = prestamoService;
        this.usuarioService = usuarioService;
        this.libroService = libroService;
    }

    @GetMapping("/prestamos")
    public String getPrestamosList(Model model){

        List<Prestamo> prestamos = prestamoService.getAll();
        model.addAttribute("prestamos", prestamos);

        return "prestamo";
    }

    //Servicio obtener nuevo un nuevo prestamo
    @GetMapping("prestamos/add")
    public String addPrestamos(Model model){

        //Me traigo la lista de libros y usuarios
        List<Libro> listaLibros = libroService.getAll();
        List<Usuario> listaUsuarios = usuarioService.getAll();

        //Le enviamos a la vista esos datos, la lista de Libros
        model.addAttribute("listaLibros", listaLibros);

        //Le enviamos a la vista esos datos, la lista de Usuarios
        model.addAttribute("listaUsuarios", listaUsuarios);

        model.addAttribute("prestamo", new Prestamo());

        return "prestamo-add";
    }

    //Servicio para guardar un nuevo Prestamo
    @PostMapping("/prestamos/save")
    public String savePrestamo(Prestamo prestamo, Model model){
        prestamoService.savePrestamo(prestamo);

        return "redirect:/prestamos";
    }

    //Servicio para obtener un prestamo a editar por id
    @GetMapping("/prestamos/edit/{id}")
    public String prestamoEdit(@PathVariable Integer id, Model model){

        //Me traigo la lista de libros y usuarios
        List<Libro> listaLibros = libroService.getAll();
        List<Usuario> listaUsuarios = usuarioService.getAll();

        //Le enviamos a la vista esos datos, la lista de Libros
        model.addAttribute("listaLibros", listaLibros);

        //Le enviamos a la vista esos datos, la lista de Usuarios
        model.addAttribute("listaUsuarios", listaUsuarios);


        Prestamo prestamoActual = prestamoService.findById(id);
        model.addAttribute("prestamoActual", prestamoActual);

        return "prestamo-edit";
    }

    //Servicio para actualizar un Prestamo
    @PostMapping("/prestamos/update")
    public String prestamoUpdate(Prestamo prestamo){
        prestamoService.updatePrestamo(prestamo);

        return "redirect:/prestamos";
    }

    //Servicio para eliminar un Libro
    @GetMapping("/prestamos/delete/{id}")
    public String prestamoDelete(@PathVariable Integer id){
        Prestamo prestamoActual = prestamoService.findById(id);
        if(prestamoActual!=null){
            prestamoService.removePrestamo(prestamoActual);
        }
        return "redirect:/prestamos";
    }

}
