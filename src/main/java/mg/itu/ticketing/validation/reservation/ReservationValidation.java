package mg.itu.ticketing.validation.reservation;

import mg.itu.prom16.winter.validation.generic.annotation.PointerValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@PointerValidator(ReservationValidator.class)

public @interface ReservationValidation {
}
