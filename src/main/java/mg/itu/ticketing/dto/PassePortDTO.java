package mg.itu.ticketing.dto;

import lombok.Getter;
import lombok.Setter;
import mg.itu.prom16.winter.annotation.WinterFile;

import javax.servlet.http.Part;

@Getter
@Setter
public class PassePortDTO {

    @WinterFile(name = "passeport")
    Part photo;

    String idTrancheAge;
}
