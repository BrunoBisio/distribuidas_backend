package distribuidas.backend.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PublishedProductsDto {
    private String productName;
    private BigDecimal basePrice;
    private BigDecimal value;
    private BigDecimal commisionPercentage;
    private BigDecimal commisionValue;
}
