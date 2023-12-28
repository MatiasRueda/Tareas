package tareas.tareas.servicio;

import java.util.List;

import tareas.tareas.domain.Tarea;

public interface TareaServicio {
    public void agregar(Tarea tarea, String nombreUsuario);
    public void borrar(Tarea tarea);
    public List<Tarea> obtener(String nombreUsuario);
    public Tarea obtenerPorID(Tarea tarea);
}
