package tareas.tareas.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import tareas.tareas.domain.Tarea;
import tareas.tareas.domain.Usuario;
import tareas.tareas.servicio.TareaServicio;
import tareas.tareas.servicio.UsuarioServicio;

@Controller
public class RestController {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private TareaServicio tareaServicio;
    private Tarea tareaElegida = new Tarea();
    private LinkedHashMap<Long, Tarea> tareas = new LinkedHashMap<>();
    private final String atras = "<";
    private boolean actualizar = false;
    private boolean errorDescripcion = false;
    private boolean errorDescripcionSize = false;
    private boolean errorNombre = false;
    private boolean errorNombreSize = false;

    //****************************** INICIO ********************************//

    @GetMapping("/")
    public String inicio(Model model, Usuario usuario, BindingResult error) {
        this.tareaElegida.limpiar();
        this.actualizar = false;
        this.errorNombre = false;
        this.errorNombreSize = false;
        this.errorDescripcion = false;
        this.errorDescripcionSize = false;
        this.tareas.clear();
        return "inicio";
    }

    @PostMapping("/iniciarSesion") 
    public String iniciar(Model model, @Valid Usuario usuario, BindingResult error) {
        if (error.hasFieldErrors("nombre") | error.hasFieldErrors("contrasenia"))
            return "inicio";
        Usuario usuarioIngresado = this.usuarioServicio.ingresar(usuario);
        if (usuarioIngresado == null) 
            return "redirect:/";
        this.tareaElegida.setUsuario(usuarioIngresado.getNombre());
        return "redirect:/tareas";
    }

    //****************************** REGISTRO ******************************//

    @GetMapping("/registrarse") 
    public String registrarse(Model model, Usuario usuario, BindingResult error) {
        model.addAttribute("atras", atras);
        return "registro";
    }

    @PostMapping("/agregarUsuario") 
    public String agregarUsuario(@Valid Usuario usuario, BindingResult error) {
        if (error.hasErrors())
            return "registro";
        if (!this.usuarioServicio.registrarse(usuario))
            return "registro";
        return "redirect:/";
    }

    //****************************** TAREA ******************************//

    private void sacarErrores() {
        this.errorNombre = false;
        this.errorNombreSize = false;
        this.errorDescripcion = false;
        this.errorDescripcionSize = false;
    }

    private String tipoError(String field, BindingResult error) {
        return error.getFieldErrors(field).stream().iterator().next().getCode();
    }

    private void crearTareas(Model model) {
        if (this.tareas.isEmpty()) 
            agregarTareas(this.tareaServicio.obtener(this.tareaElegida.getUsuario()));
        String saludo = "Bienvenido " + this.tareaElegida.getUsuario();
        String accion = actualizar? "Actualizar tarea" : "Agregar tarea";
        model.addAttribute("atras", atras);
        model.addAttribute("saludo", saludo);
        model.addAttribute("tareas", new ArrayList<>(this.tareas.values()));
        model.addAttribute("actualizar", actualizar);
        model.addAttribute("accion", accion);
        model.addAttribute("errorNombre", errorNombre);
        model.addAttribute("errorNombreSize", errorNombreSize);
        model.addAttribute("errorDescripcion", errorDescripcion);
        model.addAttribute("errorDescripcionSize", errorDescripcionSize);
        if (this.tareaElegida.getNombre() == null) 
            return;
        model.addAttribute("nombre", this.tareaElegida.getNombre());
        model.addAttribute("descripcion", this.tareaElegida.getDescripcion());
    }

    @GetMapping("/atras")
    public String volver(Usuario usuario) {
        this.tareaElegida.limpiar();
        this.actualizar = false;
        this.sacarErrores();
        this.tareas.clear();
        return "redirect:/";
    }

    private void agregarTareas(List<Tarea> tareas) {
        tareas.forEach(tarea -> {
            this.tareas.put(tarea.getId(), tarea);
        });
    }

    @GetMapping("/tareas")
    public String tareas(Model model, Tarea tarea , Usuario usuario, BindingResult error) {
        if (this.tareaElegida.getUsuario() == null)
            return "redirect:/";
        crearTareas(model);
        return "tarea";
    }

    @PostMapping("/guardar")
    public String guardar(Model model, @Valid Tarea tarea, BindingResult error) {
        if (error.hasErrors()) {
            this.errorNombre = error.hasFieldErrors("nombre");
            if (this.errorNombre && this.tipoError("nombre", error).equals("Size"))
                this.errorNombreSize = true;
            this.errorDescripcion = error.hasFieldErrors("descripcion");
            if (this.errorDescripcion && this.tipoError("descripcion", error).equals("Size"))
                this.errorDescripcionSize = true;

            return "redirect:/tareas";
        }
        this.sacarErrores();
        this.actualizar = false;
        if (this.tareaElegida.getNombre() == null) {
            this.tareaServicio.agregar(tarea, this.tareaElegida.getUsuario());
            this.tareas.put(tarea.getId(), new Tarea(tarea));
            this.tareaElegida.limpiar();
            return "redirect:/tareas";   
        }
        this.tareaElegida.setNombre(tarea.getNombre());
        this.tareaElegida.setDescripcion(tarea.getDescripcion());
        this.tareaServicio.agregar(this.tareaElegida, this.tareaElegida.getUsuario());
        this.tareas.replace(this.tareaElegida.getId(), new Tarea(this.tareaElegida));
        this.tareaElegida.limpiar();
        return "redirect:/tareas";   
    }

    @GetMapping("/agregarTarea") 
    public String agregar() {
        this.tareaElegida.limpiar();
        this.sacarErrores();
        this.actualizar = false;
        return "redirect:/tareas";  
    }

    @GetMapping("/actualizar/{id}") 
    public String actualizar(Model model, Tarea tarea) {
        this.actualizar = true;
        this.sacarErrores();
        this.tareaElegida = new Tarea(this.tareas.get(tarea.getId()));
        return "redirect:/tareas";
    }

    @GetMapping("/borrar/{id}") 
    public String borrar(Tarea tarea) {
        this.sacarErrores();
        this.tareaServicio.borrar(tarea);
        this.tareas.remove(tarea.getId());
        return "redirect:/tareas";
    }
}
