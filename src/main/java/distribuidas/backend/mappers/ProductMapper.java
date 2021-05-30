package distribuidas.backend.mappers;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.models.Product;

public class ProductMapper {

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

