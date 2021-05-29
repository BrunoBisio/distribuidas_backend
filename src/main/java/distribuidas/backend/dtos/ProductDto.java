package distribuidas.backend.dtos;

import distribuidas.backend.enums.Currency;
import distribuidas.backend.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private Currency currency;
    private State status;

}
