package mg.itu.temoin.exception;

import mg.itu.prom16.winter.validation.generic.exception.ValidationException;

public class ReservationTooLateException extends ValidationException {
    public ReservationTooLateException() {
        super("Il n'est plus possible de faire une réservation sur ce vol");
    }
}
