package mg.itu.ticketing.dto;

import lombok.Getter;
import lombok.Setter;
import mg.itu.prom16.winter.validation.annotation.RangeDouble;
import mg.itu.prom16.winter.validation.annotation.Required;
import mg.itu.ticketing.entity.avion.Avion;
import mg.itu.ticketing.entity.vol.Ville;
import mg.itu.ticketing.entity.vol.Vol;

import java.time.LocalDateTime;

@Setter
@Getter
public class VolDTO {
    @Required
    private LocalDateTime dateVol;
    @RangeDouble(min = 0, field = "prixVol")
    private double prixVol;
    private String idVille;
    private String idAvion;

    public Vol convertIntoDto(Ville destination, Avion avion){
        Vol vol=new Vol();
        vol.setPrixVol(prixVol);
        vol.setDateVol(dateVol);
        vol.setAvion(avion);
        vol.setDestination(destination);
        return vol;
    }
}
