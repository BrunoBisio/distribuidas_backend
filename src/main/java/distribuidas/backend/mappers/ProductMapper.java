package distribuidas.backend.mappers;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.enums.Currency;
import distribuidas.backend.enums.State;
import distribuidas.backend.models.Product;

public class ProductMapper {

    private int id;
    private String name;
    private String description;
    private Currency currency;
    private State status;

    public static ProductDto toDto(Product product){
        ProductDto  dto = new ProductDto();
        dto.setId(product.getId());
        dto.setCurrency(product.getCurrency());
        dto.setStatus(product.getState());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        return dto;
    }
}

