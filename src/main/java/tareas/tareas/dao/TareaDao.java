package tareas.tareas.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tareas.tareas.domain.Tarea;

public interface TareaDao extends CrudRepository<Tarea, Long>{
    List<Tarea> findByUsuario(String usuario); 
}
