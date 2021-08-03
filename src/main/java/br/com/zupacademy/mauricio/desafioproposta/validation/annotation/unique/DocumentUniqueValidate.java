package br.com.zupacademy.mauricio.desafioproposta.validation.annotation.unique;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class DocumentUniqueValidate implements ConstraintValidator<DocumentUnique, Object> {

    @Value("${criptografia.senha}")
    private String senha;
    @Value("${criptografia.chave}")
    private String chave;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isValid(Object objeto, ConstraintValidatorContext constraintValidatorContext) {
        TextEncryptor encryptor = Encryptors.queryableText(senha, chave);
        Query query = entityManager.createQuery("SELECT 1 FROM Proposta p WHERE p.documento = :pDocumento");
        query.setParameter("pDocumento", encryptor.encrypt(objeto.toString()));

        List<?> lista = query.getResultList();

        if (!lista.isEmpty()) {
            return false;
        }

        return true;
    }
}
