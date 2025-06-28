package cl.duocuc.ecomarket.util.validacion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidacionContrasena.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Contrasena {
    String message() default "Contrasena no valida, requiere al menos 8 caracteres, una mayuscula, una minuscula y un numero";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

