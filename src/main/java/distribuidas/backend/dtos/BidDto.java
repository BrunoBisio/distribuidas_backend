package distribuidas.backend.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BidDto {
    private BigDecimal ammount;
    private Date created;
}
