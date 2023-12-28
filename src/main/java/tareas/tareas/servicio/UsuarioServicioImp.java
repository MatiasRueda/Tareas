package tareas.tareas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tareas.tareas.dao.UsuarioDao;
import tareas.tareas.domain.Usuario;

@Service
public class UsuarioServicioImp implements UsuarioServicio {

    @Autowired
    private UsuarioDao usuarioDao;

    private final PasswordEncoder contraseniaEncryptada;

    public UsuarioServicioImp (UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
        this.contraseniaEncryptada = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public boolean registrarse(Usuario usuario) {
        Usuario usuarioEncontrado = this.usuarioDao.findById(usuario.getNombre()).orElse(null);
        if (usuarioEncontrado != null) 
            return false;
        String contraseniaEncryptada = this.contraseniaEncryptada.encode(usuario.getContrasenia());
        usuario.setContrasenia(contraseniaEncryptada);
        this.usuarioDao.save(usuario);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario ingresar(Usuario usuario) {
        Usuario usuarioEncontrado = this.usuarioDao.findById(usuario.getNombre()).orElse(null);
        if (usuarioEncontrado == null || !contraseniaEncryptada.matches(usuario.getContrasenia(), usuarioEncontrado.getContrasenia())) 
            return null;
        return usuarioEncontrado;
    }
}
