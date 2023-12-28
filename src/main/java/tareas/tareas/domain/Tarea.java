package tareas.tareas.domain;


import lombok.Data;
import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@Entity
@Table(name = "tarea")
public class Tarea implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min= 1, max= 20)
    private String nombre;
    private String usuario;

    @NotEmpty
    @Size(min= 1, max=55)
    private String descripcion;
    private int completado;

    public Tarea(){

    }

    public Tarea(Tarea tarea) {
        this.id = tarea.getId();
        this.nombre = tarea.getNombre();
        this.descripcion = tarea.getDescripcion();
        this.usuario = tarea.getUsuario();
        this.completado = tarea.getCompletado();
    }

    public void limpiar() {
        this.nombre = null;
        this.descripcion = null;
        this.completado = 0;
    }

}
