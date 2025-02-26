package mg.itu.ticketing.repository.generic;

import jakarta.persistence.TypedQuery;

@FunctionalInterface
public interface ParameterQuery {
    public void setParameter(TypedQuery<?> typedQuery);
}
