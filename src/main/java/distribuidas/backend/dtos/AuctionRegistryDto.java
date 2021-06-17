package distribuidas.backend.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AuctionRegistryDto {
    private String prodName;
    private Date auctionDate;
    private BigDecimal value;
}
