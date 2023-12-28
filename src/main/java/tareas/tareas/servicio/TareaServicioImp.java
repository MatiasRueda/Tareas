package tareas.tareas.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tareas.tareas.dao.TareaDao;
import tareas.tareas.domain.Tarea;

@Repository
@Service
public class TareaServicioImp implements TareaServicio {

    @Autowired
    private TareaDao tareaDao;

    @Override
    @Transactional
    public void agregar(Tarea tarea, String nombreUsuario) {
        tarea.setUsuario(nombreUsuario);
        this.tareaDao.save(tarea);
    }

    @Override
    @Transactional
    public void borrar(Tarea tarea) {
        this.tareaDao.delete(tarea);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarea> obtener(String nombreUsuario) { 
        return this.tareaDao.findByUsuario(nombreUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Tarea obtenerPorID(Tarea tarea) {
        Optional<Tarea> tareaEncontrada = this.tareaDao.findById(tarea.getId());
        return tareaEncontrada.get();
    }

}
