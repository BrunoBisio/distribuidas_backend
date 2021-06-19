package distribuidas.backend.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class BidsCreatedDto {
    private String auctionName;
    private String productName;
    private Date bidDate;
    private BigDecimal value;
    private String winner;
}
