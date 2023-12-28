package tareas.tareas.dao;

import org.springframework.data.repository.CrudRepository;
import tareas.tareas.domain.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, String> {

}
