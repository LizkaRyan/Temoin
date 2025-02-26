package mg.itu.ticketing.entity.vol;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mg.itu.ticketing.entity.avion.TypeSiege;
import mg.itu.ticketing.entity.personnel.Utilisateur;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,name = "id_reservation")
    private String idReservation;

    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;

    @Column(name = "prix_reservation")
    private double prixReservation;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vol")
    private Vol vol;

    @Column(name = "src_photo")
    private String srcPhoto;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_type_siege")
    private TypeSiege typeSiege;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    public void setPhotoSrc(Part photo) throws IOException {
        String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
        // Sauvegarde du fichier
        String uploadPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Temoin\\public\\images\\"+ UUID.randomUUID().toString()+"." + fileName;
        Files.copy(photo.getInputStream(), Paths.get(uploadPath));
        this.srcPhoto=uploadPath;
    }
}
