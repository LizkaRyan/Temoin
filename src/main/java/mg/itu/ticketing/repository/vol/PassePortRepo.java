package mg.itu.ticketing.repository.vol;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import mg.itu.ticketing.dto.PassePortDTO;
import mg.itu.ticketing.entity.vol.Passeport;
import mg.itu.ticketing.entity.vol.Reservation;
import mg.itu.ticketing.entity.vol.parametre.TrancheAgePromotion;
import mg.itu.ticketing.repository.generic.GenericRepository;
import mg.itu.ticketing.repository.vol.parametre.TrancheAgePromotionRepo;
import mg.itu.ticketing.repository.vol.parametre.TrancheAgeRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PassePortRepo extends GenericRepository<Passeport,String> {
    private final TrancheAgeRepo trancheAgeRepo=new TrancheAgeRepo();
    private final TrancheAgePromotionRepo trancheAgePromotionRepo=new TrancheAgePromotionRepo();
    private final VolRepository volRepository=new VolRepository();

    public List<Passeport> createPassePortByDTO(List<PassePortDTO> passePortDTOs, Reservation reservation, EntityManager em) throws IOException {
        List<Passeport> passeports=new ArrayList<>();
        Long nbPassager = volRepository.findNbPassagerByIdVol(reservation.getVol().getIdVol(),em);
        for (PassePortDTO passePortDTO:passePortDTOs){
            Passeport passeport=new Passeport(reservation);
            passeport.setPhotoSrc(passePortDTO.getPhoto());
            passeport.setTrancheAge(trancheAgeRepo.findTrancheById(passePortDTO.getIdTrancheAge(),em));
            setPrixReservation(passeport,reservation,(int)(nbPassager+1),em);
            nbPassager++;
            reservation.setPrixReservation(reservation.getPrixReservation()+passeport.getPrixReservation());
            passeports.add(passeport);
        }
        return passeports;
    }

    public Passeport setPrixReservation(Passeport passeport,Reservation reservation,int nthPlace,EntityManager em) {
        double prix = reservation.getVol().getPrixVol();
        prix += reservation.getTypeSiege().getPrixSiege();
        if(nthPlace<reservation.getTypeSiege().getNbSiegePromotion()){
            prix-=prix*reservation.getTypeSiege().getPromotion()/100;
        }
        TrancheAgePromotion trancheAgePromotion = trancheAgePromotionRepo.findPromotionByIdTrancheAge(passeport.getTrancheAge().getIdTrancheAge(),em);
        passeport.setPrixReservation(prix+(prix*trancheAgePromotion.getPourcentage()/100));
        return passeport;
    }
}
