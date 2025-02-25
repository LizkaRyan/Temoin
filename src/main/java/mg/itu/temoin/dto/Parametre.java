package mg.itu.temoin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.itu.prom16.winter.validation.annotation.RangeInt;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parametre {
    @RangeInt(min=0,field="reservation")
    private int reservation;
    @RangeInt(min=0,field="annulation")
    private int annulation;
}
