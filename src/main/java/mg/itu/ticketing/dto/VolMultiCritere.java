package mg.itu.ticketing.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VolMultiCritere {
    private String idVille;
    private LocalDateTime dateTimeMin;
    private LocalDateTime dateTimeMax;
}
