package cl.duocuc.ecomarket.util.validacion;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@NotNull
@NotBlank
@ReportAsSingleViolation
@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Requerido {
    String message() default "El valor no puede ser nulo o vacío";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
