package distribuidas.backend.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AuctionsWonDto {
    private String auctionName;
    private String productName;
    private String currency;
    private BigDecimal value;
    private Date auctionDate;
}
