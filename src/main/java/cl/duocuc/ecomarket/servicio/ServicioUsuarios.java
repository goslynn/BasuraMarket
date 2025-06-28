package cl.duocuc.ecomarket.servicio;

import cl.duocuc.ecomarket.modelo.PersistenciaSP;
import cl.duocuc.ecomarket.modelo.dto.usuario.*;
import cl.duocuc.ecomarket.modelo.entity.usuario.*;
import cl.duocuc.ecomarket.modelo.mapper.UsuarioMapper;
import cl.duocuc.ecomarket.modelo.repository.PermisoRepository;
import cl.duocuc.ecomarket.modelo.repository.RolRepository;
import cl.duocuc.ecomarket.modelo.repository.RolesPermisoRepository;
import cl.duocuc.ecomarket.modelo.repository.UsuarioRepository;
import cl.duocuc.ecomarket.tipodatos.TipoCuenta;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import cl.duocuc.ecomarket.util.encriptacion.Encriptador;
import cl.duocuc.ecomarket.util.exception.ApiException;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;


@Service
public class ServicioUsuarios {
    private final Encriptador<String> encriptador;

    private final UsuarioRepository userRepo;
    private final RolRepository rolRepo;
    private final PermisoRepository permisoRepo;
    private final RolesPermisoRepository rolesPermisoRepo;

    @PersistenceContext
    private EntityManager em;
    private PersistenciaSP persistencia;
    private static final Logger log = LoggerFactory.getLogger(ServicioUsuarios.class);

    public ServicioUsuarios(UsuarioRepository userRepo,
                            RolRepository rolRepo,
                            PermisoRepository permisoRepo,
                            RolesPermisoRepository rolesPermisoRepo,
                            Encriptador<String> encriptador) {
        this.userRepo = userRepo;
        this.rolRepo = rolRepo;
        this.permisoRepo = permisoRepo;
        this.rolesPermisoRepo = rolesPermisoRepo;
        this.encriptador = encriptador;
    }


    @PostConstruct
    public void init() {
        this.persistencia = new PersistenciaSP(em);
    }


    public UsuarioResponseDTO obtenerUsuario(Integer id) throws ApiException{
        return userRepo.findById(id)
                       .filter(Usuario::getActivo)
                       .map(UsuarioMapper::getResponseDTO)
                       .orElseThrow(() -> new ApiException(404, String.format("el usuario con id {%d} no existe", id))
        );
    }

    /**
     * Registra un empleado, llama al procedimiento almacenado
     * pasandole solo los datos correspondientes a empleado, dejando
     * nulos los correspondientes a cliente
     * @param e Empleado a registrar
     * @return 'JSON' de respuesta para la api
     * @throws ApiException Error lanzado ante error de validacion o de ejecucion del procedimiento
     */
    public UsuarioResponseDTO registrar(EmpleadoRequestDTO e) throws ApiException {
        return persistencia.agregarUsuario(
                null,  // Teléfono (no aplica para empleados)
                e.nombre(),
                e.correo(),
                e.fechaNacimiento(),
                encriptador.encriptar(e.contrasena()),
                String.valueOf(e.genero().toChar()),
                e.rut(),
                e.fechaContratacion(),
                e.cargoEmpleado(),
                e.AreaEmpleado(),
                e.idRolUsuario(),
                TipoCuenta.EMPLEADO.getId()
        );
    }

    /**
     * Registra un cliente, llama al procedimiento almacenado
     * pasandole solo los datos correspondientes a cliente, dejando
     * nulos los correspondientes a empleado
     * @param c Cliente a registrar
     * @return 'JSON' de respuesta para la api
     * @throws ApiException Error lanzado ante error de validacion o de ejecucion del procedimiento
     */
    public UsuarioResponseDTO registrar(ClienteRequestDTO c) throws ApiException {
        return persistencia.agregarUsuario(
                c.telefono(),
                c.nombre(),
                c.correo(),
                c.fechaNacimiento(),
                encriptador.encriptar(c.contrasena()),
                String.valueOf(c.genero().toChar()),
                "",  // RUT (no aplica para clientes)
                "",  // Fecha de contratación (no aplica para clientes)
                "",  // Cargo (no aplica para clientes)
                "",  // Área (no aplica para clientes)
                c.idRolUsuario(),
                TipoCuenta.CLIENTE.getId()
        );
    }

    /**
     * Aca se declara el metodo de el put y nos asguramos que cuando se pase una peticion con un id que no es el que
     * corresponda arroje error de que no existe
     * @param id
     * @param u
     * @throws ApiException
     */
    public CodigoDescripcion<Integer,String> actualizar(Integer id, UsuarioRequestDTO u) throws ApiException {
        if (!userRepo.existsById(id)){
            throw new ApiException(404, "el usuario no existe");
        }
        persistencia.actualizarUsuario(
                id,
                u.nombre(),
                u.correo(),
                encriptador.encriptado(u.contrasenaHash()) ?
                u.contrasenaHash() : encriptador.encriptar(u.contrasenaHash()),
                u.fechaNacimiento(),
                String.valueOf(u.genero().toChar()),
                u.idRol(),
                u.telefono(),
                u.rutEmpleado(),
                u.fechaContratacion(),
                u.cargoEmpleado(),
                u.areaEmpleado());

        return CodigoDescripcion.of(id, "Usuario actualizado correctamente");
    }

    public void desactivarUsuario(Integer id){
        if (!userRepo.existsById(id)){
            throw new RuntimeException("el usuario no existe");
        }
        userRepo.findById(id).map(bd -> {
            bd.setActivo(false);
            return userRepo.save(bd);
        });
    }


    @Transactional
    public RolDTO crearRol(RolPermisosRequestDTO dto) throws ApiException {
        Rol rol = dto.toEntidad();
        rolRepo.save(rol);
        rolesPermisoRepo.saveAll(rol.getRolesPermisos());
        return RolDTO.fromEntidad(rol);
    }

    public RolDTO obtenerRol(Integer id)  {
        return rolRepo.findById(id)
                .map(RolDTO::fromEntidad)
                .orElseThrow(() -> new ApiException(404, "Rol con ID " + id + " no encontrado"));
    }


    //    @Transactional(readOnly = true)
    public List<RolDTO> obtenerRoles() throws ApiException {
        List<RolDTO> roles = rolRepo.findAll().stream()
                                    .filter(Rol::getActivo)
                                    .map(RolDTO::fromEntidad)
                                    .toList();

        if (roles.isEmpty()) {
            throw new ApiException(404, "No hay roles activos disponibles.");
        }

        return roles;
    }

//    @Transactional(readOnly = true)
    public RolPermisosResponseDTO obtenerRolPermisos(Integer id) throws ApiException {
        Rol rol = buscarRol(id);
        return RolPermisosResponseDTO.fromEntidad(rol);
    }

    @Transactional
    protected List<Permiso> buscarPermisos(Usuario usuario) throws ApiException {
        return buscarPermisos(usuario.getRol());
    }

    @Transactional
    protected List<Permiso> buscarPermisos(Integer idRol) throws ApiException {
        return buscarPermisos(rolRepo.findById(idRol)
                                     .filter(Rol::getActivo)
                                     .orElseThrow(() -> new ApiException(404, "Rol con ID " + idRol + " no encontrado")));
    }

    @Transactional
    protected List<Permiso> buscarPermisos(Rol rol) throws ApiException {
        return rol.getRolesPermisos().stream()
                .map(RolesPermiso::getPermiso)
                .filter(Permiso::getActivo)
                .toList();
    }

    protected Rol buscarRol(Integer id) throws ApiException {
        return rolRepo.findById(id)
                .orElseThrow(() -> new ApiException(404, "Rol con ID " + id + " no encontrado"));
    }




    @Transactional
    public void eliminarRol(Integer id) throws ApiException {
        if (!rolRepo.existsById(id)) {
            throw new ApiException(404, "El rol no existe");
        }
        rolRepo.deleteById(id);
    }

    @Transactional
    public CodigoDescripcion<Integer,String> actualizarRol(Integer id, RolPermisosRequestDTO dto) throws ApiException {
        Rol rol = rolRepo.findById(id)
                .orElseThrow(() -> new ApiException(404, "Rol con ID " + id + " no encontrado"));

        rol.setNombreRol(dto.nombre());
        rol.setDescripcion(dto.descripcion());

        /*
        TODO: @HAPEMA refactorizar la forma de eliminar rolesPermiso iterando sobre el dto
        e instanciando un objeto (id) RolesPermisoId, para luego eliminarlo, tal y como lo hace
        mas abajo para guardar, que lo haga de la misma forma para borrar.
         */

        rolRepo.save(rol);

        rolesPermisoRepo.deleteAll(rolesPermisoRepo.findAllByRol_Id(id));

        for (Integer idPermiso : dto.permisos()) {
            Permiso permiso = permisoRepo.findById(idPermiso)
                    .orElseThrow(() -> new ApiException(404, "Permiso con ID " + idPermiso + " no encontrado"));
            RolesPermiso rp = new RolesPermiso();
            rp.setId(new RolesPermisoId(id, idPermiso));
            rp.setRol(rol);
            rp.setPermiso(permiso);
            rolesPermisoRepo.save(rp);
        }
        return CodigoDescripcion.of(id, "Rol actualizado correctamente");
    }



    public List<PermisoResponseDTO> obtenerPermisos() throws ApiException{
        List<PermisoResponseDTO> permisos = permisoRepo.findAll().stream()
                                    .filter(Permiso::getActivo)
                                    .map(PermisoResponseDTO::fromEntidad)
                                    .toList();

        if (permisos.isEmpty()) {
            throw new ApiException(404, "No hay permisos activos disponibles.");
        }

        return permisos;
    }

    public UsuarioRepository getUserRepo() {
        return userRepo;
    }

    public RolRepository getRolRepo() {
        return rolRepo;
    }

    public PermisoRepository getPermisoRepo() {
        return permisoRepo;
    }

    public RolesPermisoRepository getRolesPermisoRepo() {
        return rolesPermisoRepo;
    }

    public void setPersistencia(PersistenciaSP persistencia) {
        this.persistencia = persistencia;
    }
}
