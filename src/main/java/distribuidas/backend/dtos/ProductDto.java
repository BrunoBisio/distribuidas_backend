package distribuidas.backend.dtos;

import java.math.BigDecimal;
import java.util.List;

import distribuidas.backend.enums.Admited;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private Admited available;
    private List<String> photos;
    private BigDecimal initialPrice;
    private String fullDescription;
    private BigDecimal commisionValue;
    private BigDecimal soldPrice;
    private String prodState;
    private long timeBeforeClose;
    private BidDto latestBid;
    private boolean isAuctionOpen;
}
