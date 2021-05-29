package distribuidas.backend.dtos;

import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.State;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class AuctionDto {
    private int id;
    private State status;
    private String description;
    private Category category;
    private Date startDate;
    private List<ProductDto> productDtos;
}
