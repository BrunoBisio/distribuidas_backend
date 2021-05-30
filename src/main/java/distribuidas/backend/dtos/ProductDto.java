package distribuidas.backend.dtos;

import java.util.List;

import distribuidas.backend.enums.Currency;
import distribuidas.backend.enums.State;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private Currency currency;
    private State status;
    private List<String> photos;
}
