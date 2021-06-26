package distribuidas.backend.mappers;

import java.util.stream.Collectors;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.models.AuctionRegistry;
import distribuidas.backend.models.Photo;
import distribuidas.backend.models.Product;

public class ProductMapper {

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setAvailable(product.getAvailable());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        if (product.getPhotos() != null && product.getPhotos().size() > 0)
            dto.setPhotos(product.getPhotos().stream().map(Photo::getPhoto).collect(Collectors.toList()));
        dto.setInitialPrice(product.getPrice());
        dto.setFullDescription(product.getFullDescription());
        return dto;
    }

    public static ProductDto toPendingDto(Product product) {
        ProductDto dto = toDto(product);
        dto.setProdState("pending");
        return dto;
    }

    public static ProductDto toDeletableDto(Product product) {
        ProductDto dto = toDto(product);
        dto.setProdState("pending and deletable");
        return dto;
    }

    public static ProductDto toSoldDto(Product product, AuctionRegistry ar) {
        ProductDto dto = toDto(product);
        dto.setSoldPrice(ar.getAmmount());
        dto.setCommisionValue(ar.getCommission());
        return dto;
    }
    
}

