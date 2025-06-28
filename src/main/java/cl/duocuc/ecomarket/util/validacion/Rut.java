package cl.duocuc.ecomarket.util.validacion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidadorRut.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Rut {
    String message() default "El rut no es valido, asegurate de que no tenga puntos y si tenga guion";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
