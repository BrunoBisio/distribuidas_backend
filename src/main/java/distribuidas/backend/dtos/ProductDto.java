package distribuidas.backend.dtos;

import java.math.BigDecimal;
import java.util.List;

import distribuidas.backend.enums.Currency;
import distribuidas.backend.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private Currency currency;
    private State status;
    private List<String> photos;
    private BigDecimal initialPrice;
}
