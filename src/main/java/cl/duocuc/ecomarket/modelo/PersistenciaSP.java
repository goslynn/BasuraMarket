package cl.duocuc.ecomarket.modelo;


import cl.duocuc.ecomarket.modelo.dto.usuario.UsuarioResponseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;


public class PersistenciaSP {

    private final EntityManager em;

    public PersistenciaSP(EntityManager em) {
        this.em = em;
    }

    public UsuarioResponseDTO agregarUsuario(
            String telefono,
            String nombre,
            String correo,
            String fechaNacimiento,
            String contrasenaHash,
            String genero,
            String rut,
            String fechaContratacion,
            String cargoEmpleado,
            String areaEmpleado,
            Integer rolUsuario,
            Integer tipoUsuario
    ) {
        final StoredProcedureQuery sql = initParametersAgregarUsuario(em.createStoredProcedureQuery("usuario.usp_agregar_usuario"));
        sql.setParameter(1, telefono);
        sql.setParameter(2, nombre);
        sql.setParameter(3, correo);
        sql.setParameter(4, fechaNacimiento);
        sql.setParameter(5, contrasenaHash);
        sql.setParameter(6, genero);
        sql.setParameter(7, rut);
        sql.setParameter(8, fechaContratacion);
        sql.setParameter(9, cargoEmpleado);
        sql.setParameter(10, areaEmpleado);
        sql.setParameter(14, rolUsuario);
        sql.setParameter(15, tipoUsuario);

        sql.execute();

        return new UsuarioResponseDTO(
                (Integer) sql.getOutputParameterValue(11),
                (Integer) sql.getOutputParameterValue(12),
                (String) sql.getOutputParameterValue(13)
        );
    }


    private StoredProcedureQuery initParametersAgregarUsuario(StoredProcedureQuery sql) {
        sql.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);  // c_telefono_cliente
        sql.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);  // x_nombre_completo
        sql.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);  // x_correo_cliente
        sql.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);  // x_fecha_nacimiento
        sql.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);  // x_contrasena_hash
        sql.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);  // x_genero
        sql.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);  // r_rut_empleado
        sql.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);  // r_fecha_contratacion
        sql.registerStoredProcedureParameter(9, String.class, ParameterMode.IN);  // r_cargo_empleado
        sql.registerStoredProcedureParameter(10, String.class, ParameterMode.IN); // r_area_empleado

        sql.registerStoredProcedureParameter(11, Integer.class, ParameterMode.OUT); // o_id_usuario
        sql.registerStoredProcedureParameter(12, Integer.class, ParameterMode.OUT); // o_id_rol_usuario
        sql.registerStoredProcedureParameter(13, String.class, ParameterMode.OUT);  // o_nombre_completo

        sql.registerStoredProcedureParameter(14, Integer.class, ParameterMode.IN); // x_rol_usuario
        sql.registerStoredProcedureParameter(15, Integer.class, ParameterMode.IN); // x_tipo_usuario

        return sql;
    }


    /**
     *
     * Este es el USP de Actualizar Una cuenta, esta conectado tanto al usp usuario.usp_actualizar_usuario
     * que esta en el schema de usuario, este se encarga de actualizar la cuenta y si se pasa los parametros indicados actualiza
     * tambien los datos de cliente o de empleado
     * @param id
     * @param usuario
     */
    public void actualizarUsuario(
            Integer id,
            String nombre,
            String correo,
            String contrasenaHash,
            String fechaNacimiento,
            String genero,
            Integer idRol,
            String telefono,
            String rutEmpleado,
            String fechaContratacion,
            String cargoEmpleado,
            String areaEmpleado
            ) {
        StoredProcedureQuery sql = initParametersActualizarUsuario(
                em.createStoredProcedureQuery("usuario.usp_actualizar_usuario")
        );

        // Conversión de Long a Integer
        sql.setParameter(1, id);
        sql.setParameter(2, nombre);
        sql.setParameter(3, correo);
        sql.setParameter(4, contrasenaHash );
        sql.setParameter(5, fechaNacimiento);
        sql.setParameter(6, genero);
        sql.setParameter(7, idRol);
        sql.setParameter(8, telefono);
        sql.setParameter(9, rutEmpleado);
        sql.setParameter(10, fechaContratacion);
        sql.setParameter(11, cargoEmpleado);
        sql.setParameter(12, areaEmpleado);

        // Ejecuta el SP
        sql.execute();
    }

    private StoredProcedureQuery initParametersActualizarUsuario(StoredProcedureQuery sql) {
        // Parámetros de entrada
        sql.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);   // p_id_usuario
        sql.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // p_nombre_usuario
        sql.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // p_correo_usuario
        sql.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // p_contrasena_hash
        sql.registerStoredProcedureParameter(5, String.class, ParameterMode.IN); // p_fecha_nacimiento
        sql.registerStoredProcedureParameter(6, String.class, ParameterMode.IN); // p_genero
        sql.registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN); // p_id_rol_usuario
        sql.registerStoredProcedureParameter(8, String.class, ParameterMode.IN); // p_telefono_cliente
        sql.registerStoredProcedureParameter(9, String.class, ParameterMode.IN); // p_rut_empleado
        sql.registerStoredProcedureParameter(10, String.class, ParameterMode.IN); // p_fecha_contratacion
        sql.registerStoredProcedureParameter(11, String.class, ParameterMode.IN); // p_cargo_empleado
        sql.registerStoredProcedureParameter(12, String.class, ParameterMode.IN); // p_area_empleado

        return sql;
    }








}
