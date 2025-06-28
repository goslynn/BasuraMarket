package cl.duocuc.ecomarket.seguridad;

public interface ProveedorJWT {

    String generarToken(Number idUsuario, int idRol);

    boolean validarToken(String token);

    Integer getIdUsuario(String token);

    <T> T getClaim(String token, String claimName);
}
