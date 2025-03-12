package mg.itu.ticketing.exception;

import mg.itu.prom16.winter.validation.generic.ValidationException;

public class ReservationTooLateException extends ValidationException {
    public ReservationTooLateException() {
        super("Il n'est plus possible de faire une réservation sur ce vol");
    }
}
