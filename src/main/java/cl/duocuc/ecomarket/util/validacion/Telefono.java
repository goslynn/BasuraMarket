package cl.duocuc.ecomarket.util.validacion;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidadorTelefono.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Telefono {
    String message() default "El numero de telefono no es valido, se espera un formato '+56912345678' o '912345678'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
