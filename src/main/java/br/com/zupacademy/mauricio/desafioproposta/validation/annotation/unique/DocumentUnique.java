package br.com.zupacademy.mauricio.desafioproposta.validation.annotation.unique;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = DocumentUniqueValidate.class)
public @interface DocumentUnique {

    String message() default "já existe!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}