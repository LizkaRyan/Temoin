package mg.itu.ticketing.exception;

import mg.itu.prom16.winter.validation.generic.ValidationException;

public class NoSeatsAvailableException extends ValidationException {
    public NoSeatsAvailableException() {
        super("Il n'y a plus de siege que vous avez demandé dans ce vol");
    }
}
