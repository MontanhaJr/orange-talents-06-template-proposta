package br.com.zupacademy.mauricio.desafioproposta.validation.annotation.biometria;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class Base64Validate implements ConstraintValidator<Base64Validator, Object> {

    @Override
    public boolean isValid(Object objeto, ConstraintValidatorContext constraintValidatorContext) {

        byte[] bytes = Base64.decodeBase64URLSafe(objeto.toString());
        String codificadoNovamente = new String(Base64.encodeBase64(bytes));

        if (objeto.equals(codificadoNovamente)) {
            return true;
        }
        return false;
    }
}
