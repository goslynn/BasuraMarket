package cl.duocuc.ecomarket.util.validacion;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;


@Size(min = 8, max = 8, message = "Largo ilegal de fecha, minimo 8 caracteres, maximo 8 caracteres")
@Documented
@Constraint(validatedBy = ValidadorFechaDB.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaDB {
    String message() default "La fecha no es válida, debe tener un formato 'YYYYMMDD'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
