package distribuidas.backend.dtos;

import java.math.BigDecimal;
import java.util.List;

import distribuidas.backend.enums.Admited;
import distribuidas.backend.enums.Currency;

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
    private Admited status;
    private List<String> photos;
    private BigDecimal initialPrice;
}
