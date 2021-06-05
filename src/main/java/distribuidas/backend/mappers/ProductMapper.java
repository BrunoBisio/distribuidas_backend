package distribuidas.backend.mappers;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.models.Photo;
import distribuidas.backend.models.Product;

import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDto toDto(Product product){
        ProductDto  dto = new ProductDto();
        dto.setId(product.getId());
        dto.setCurrency(product.getCurrency());
        dto.setStatus(product.getAvailable());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPhotos(product.getPhotos().stream().map(Photo::getPhoto).collect(Collectors.toList()));
        return dto;
    }
}

