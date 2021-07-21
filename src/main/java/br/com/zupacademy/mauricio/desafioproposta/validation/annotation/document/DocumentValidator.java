package br.com.zupacademy.mauricio.desafioproposta.validation.annotation.document;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR) // Especifica que será feito uma comparação do tipo OU e não E
@ReportAsSingleViolation // Reporta o erro individualmente
@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { }) // Nessa caso não é necessário uma classe validadora
public @interface DocumentValidator {

    String message() default "CPF ou CNPJ inválido!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
