package pe.isil.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.model.Usuario;
import pe.isil.service.UsuarioService;

import java.util.List;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Servicio Listar Libros
    //1. cuando entra a este metodo se apoya del service
    //llama al service y obtiene la lista de libros
    //luego recogemos el model y le agregamos atributos"libros" y le pasamos libros
    //luego nos vamos a libros.html, vamos a recoger el valor
    //th:switch, los que esta entre parentesis debe ser lo mismo que arriba esta en parentesis
    @GetMapping("/usuarios")
    public String getUsuariosList(Model model){
        List<Usuario> usuarios = usuarioService.getAll();
        model.addAttribute("usuarios", usuarios);

        return "usuario";
    }

    ////Servicio obtener nuevo el modal e ingresar los datos del nuevo libro que queramos
    @GetMapping("usuarios/add")
    public String addUsuario(Model model){

        model.addAttribute("usuario", new Usuario());
        return "usuario-add";
    }

    //Servicio para guardar un nuevo Libro
    @PostMapping("/usuarios/save")
    public String saveUsuario(Usuario usuario, Model model){

        usuarioService.saveUsuario(usuario);

        return "redirect:/usuarios";
    }

    //Servicio para obtener un usuario y editar por id
    @GetMapping("/usuarios/edit/{id}")
    public String usuarioEdit(@PathVariable Integer id, Model model){

        Usuario usuarioActual = usuarioService.findById(id);
        model.addAttribute("usuariosActual", usuarioActual);

        return "usuario-edit";
    }


    //Servicio para actualizar un usuario
    @PostMapping("/usuarios/update")
    public String usuariosUpdate(Usuario usuario){

        usuarioService.updateUsuario(usuario);

        return "redirect:/usuarios";
    }

    //Servicio para eliminar un Usuario
    @GetMapping("/usuarios/delete/{id}")
    public String usuarioDelete(@PathVariable Integer id){
        Usuario usuarioActual = usuarioService.findById(id);
        if(usuarioActual!=null){
            usuarioService.removeUsuario(usuarioActual);
        }
        return "redirect:/usuarios";
    }

}