package mg.itu.temoin.repository;

import mg.itu.temoin.entity.avion.Modele;
import mg.itu.temoin.repository.generic.GenericRepository;

import java.util.List;

public class ModeleRepository extends GenericRepository<Modele,String> {
    public ModeleRepository(){
        super(Modele.class);
    }

    public List<Modele> findAll(){
        return this.findRequest("select m from Modele m");
    }
}
