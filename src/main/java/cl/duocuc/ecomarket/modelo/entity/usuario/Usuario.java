package cl.duocuc.ecomarket.modelo.entity.usuario;

import cl.duocuc.ecomarket.modelo.entity.EntidadEcomarket;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "usuario", schema = "usuario")
public class Usuario extends EntidadEcomarket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('usuario.usuario_id_usuario_seq')")
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;

    @Size(max = 255)
    @NotNull
    @Column(name = "correo_usuario", nullable = false)
    private String correoUsuario;

    @Size(max = 255)
    @NotNull
    @Column(name = "contrasena_hash", nullable = false)
    private String contrasenaHash;

    @Size(max = 8)
    @Column(name = "fecha_nacimiento", length = 8)
    private String fechaNacimiento;

    @Column(name = "genero", length = Integer.MAX_VALUE)
    private String genero;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rol_usuario", nullable = false)
    private Rol Rol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Rol getRol() {
        return Rol;
    }

    public void setRol(Rol idRolUsuario) {
        this.Rol = idRolUsuario;
    }


}