package br.com.zupacademy.mauricio.desafioproposta.validation.annotation.biometria;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = Base64Validate.class)
public @interface Base64Validator {

    String message() default "Não está no formato Base64!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}