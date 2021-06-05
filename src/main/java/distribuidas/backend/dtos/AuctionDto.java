package distribuidas.backend.dtos;

import java.util.Date;
import java.util.List;

import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.State;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuctionDto {
    private int id;
    private State status;
    private String description;
    private Category category;
    private Date startDate;
    private List<ProductDto> productDtos;
    private String photo;
    private String name;
    private int quantity;
}
