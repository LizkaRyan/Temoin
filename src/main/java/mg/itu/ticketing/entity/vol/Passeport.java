package mg.itu.ticketing.entity.vol;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.itu.ticketing.entity.vol.parametre.TrancheAge;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Entity
@Table(name = "passeport")
@NoArgsConstructor
@Setter
@Getter
public class Passeport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_passeport",insertable = false)
    private String idPasseport;

    @Column(name = "src_photo")
    private String srcPhoto;

    @Column(name = "prix_reservation")
    private double prixReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tranche_age")
    private TrancheAge trancheAge;

    public Passeport(Reservation reservation){
        this.reservation=reservation;
    }

    public void setPhotoSrc(Part photo) throws IOException {
        String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
        // Sauvegarde du fichier
        String uploadPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Temoin\\public\\images\\"+ UUID.randomUUID().toString()+"." + fileName;
        Files.copy(photo.getInputStream(), Paths.get(uploadPath));
        this.srcPhoto=uploadPath;
    }
}
