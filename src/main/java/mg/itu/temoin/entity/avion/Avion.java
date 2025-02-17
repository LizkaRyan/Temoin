package mg.itu.temoin.entity.avion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false,name = "id_avion")
    private String idAvion;

    private String avion;

    @Column(name = "date_fabrication")
    private LocalDate dateFabrication;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_modele")
    private Modele modele;
}
