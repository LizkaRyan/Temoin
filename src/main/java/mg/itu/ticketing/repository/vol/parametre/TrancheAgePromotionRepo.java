package mg.itu.ticketing.repository.vol.parametre;

import jakarta.persistence.EntityManager;
import mg.itu.ticketing.entity.vol.parametre.TrancheAgePromotion;
import mg.itu.ticketing.repository.generic.GenericRepository;

import java.util.Optional;

public class TrancheAgePromotionRepo extends GenericRepository<TrancheAgePromotion,String> {

    public TrancheAgePromotion findPromotionByIdTrancheAge(String idTrancheAge, EntityManager entityManager){
        return super.findOnlyOne("select t from TrancheAgePromotion t where t.trancheAge.idTrancheAge = :idTrancheAge",(typedQuery -> {
            typedQuery.setParameter("idTrancheAge",idTrancheAge);
        }),entityManager).orElseThrow(()->new RuntimeException("Id tranche age non trouve"));
    }
}
