package mg.itu.temoin.validation;

import mg.itu.prom16.winter.validation.generic.annotation.PointerValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@PointerValidator(VolValidator.class)
public @interface VolValidation {
}
