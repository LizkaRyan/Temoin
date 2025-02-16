package mg.itu.temoin.dto;

import lombok.Getter;
import lombok.Setter;
import mg.itu.prom16.winter.validation.annotation.RangeInt;
import mg.itu.temoin.entity.avion.Avion;
import mg.itu.temoin.entity.vol.Ville;
import mg.itu.temoin.entity.vol.Vol;

import java.time.LocalDateTime;

@Setter
@Getter
public class VolDTO {
    private LocalDateTime dateVol;
    @RangeInt(min = 0, field = "prixVol")
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
