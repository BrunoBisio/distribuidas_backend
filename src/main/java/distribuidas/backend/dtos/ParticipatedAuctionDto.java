package distribuidas.backend.dtos;

import java.util.Date;

import distribuidas.backend.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ParticipatedAuctionDto {
    private String auctionName;
    private String productName;
    private String currency;
    private Category category;
    private Date auctionDate;
}
