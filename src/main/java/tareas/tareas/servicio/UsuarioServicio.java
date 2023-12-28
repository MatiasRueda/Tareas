package tareas.tareas.servicio;

import tareas.tareas.domain.Usuario;

public interface UsuarioServicio {
    public boolean registrarse(Usuario usuario);
    public Usuario ingresar(Usuario usuario);
}
