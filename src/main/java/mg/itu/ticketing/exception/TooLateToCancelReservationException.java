package mg.itu.ticketing.exception;

import mg.itu.prom16.winter.validation.generic.exception.ValidationException;

public class TooLateToCancelReservationException extends ValidationException {

    public TooLateToCancelReservationException() {
        super("Il n'est plus possible d'annuler la réservation");
    }
}
