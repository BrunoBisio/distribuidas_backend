package distribuidas.backend.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BasicAuctionDto {
    private String name;
    private int quantity;
    private Date startDate;
    private String photo;
}
