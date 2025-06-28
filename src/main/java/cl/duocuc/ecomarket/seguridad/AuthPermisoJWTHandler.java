package cl.duocuc.ecomarket.seguridad;

import cl.duocuc.ecomarket.funcional.RequierePermiso;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import cl.duocuc.ecomarket.servicio.ServicioAuth;
import cl.duocuc.ecomarket.tipodatos.TipoPermiso;
import cl.duocuc.ecomarket.util.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthPermisoJWTHandler implements HandlerInterceptor {

    private final ServicioAuth servicio;
    private static final Logger log = LoggerFactory.getLogger(AuthPermisoJWTHandler.class);

    public AuthPermisoJWTHandler(ServicioAuth servicio) {
        this.servicio = servicio;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod metodo) {
            RequierePermiso anotacionPermiso = metodo.getMethodAnnotation(RequierePermiso.class);
            if (anotacionPermiso == null) {
                log.debug("No se requiere autorización para el método: {}", metodo.getMethod().getName());
                return true;
            }
            return autorizar(resolverToken(request), anotacionPermiso.value());
        }
        log.warn("No se requiere autorización para el handler: {}", handler.getClass().getName());
        return true;
    }

    private String resolverToken(HttpServletRequest request) {
        return request.getHeader("Authorization") != null
                ? request.getHeader("Authorization").replace("Bearer ", "")
                : null;
    }

    private boolean autorizar(String token, TipoPermiso requiere) throws ApiException {
        if (token == null || token.isBlank()) {
            throw new ApiException(401, "Token de autorización no encontrado o mal formado. Asegúrese de incluir el prefijo 'Bearer ' en el encabezado Authorization.");
        }
        if (!servicio.isAutorizado(token, requiere)) {
            Usuario u = servicio.getUsuario(token);
            log.warn("La operacion {} no esta autorizada para el usuario: {}", requiere, u.getId());
            throw new ApiException(
                    401, String.format("Operacion no autorizada, no posee los permisos necesarios para realizar esta accion. ( USUARIO: {%d-%s} , PETICION: %s ) ",
                    u.getId(), u.getNombreUsuario(), requiere.toString())
            );
        }
        return true;
    }

}
