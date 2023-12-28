package tareas.tareas.domain;

import lombok.Data;
import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Id
    @Size(min= 1, max= 10)
    private String nombre;

    @NotEmpty
    @Size(min= 1, max= 255)
    private String contrasenia;

    @NotEmpty
    @Size(min= 1, max= 10)
    private String DNI;

    @NotEmpty
    @Email
    private String email;
}
