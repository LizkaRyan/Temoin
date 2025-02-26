package mg.itu.ticketing.repository;

import mg.itu.ticketing.entity.avion.Modele;
import mg.itu.ticketing.repository.generic.GenericRepository;

import java.util.List;

public class ModeleRepository extends GenericRepository<Modele,String> {
    public List<Modele> findAll(){
        return this.findRequest("select m from Modele m");
    }
}
