package mg.itu.temoin.repository.generic;

import jakarta.persistence.TypedQuery;
import mg.itu.temoin.entity.avion.Modele;

@FunctionalInterface
public interface ParameterQuery {
    public void setParameter(TypedQuery<?> typedQuery);
}
