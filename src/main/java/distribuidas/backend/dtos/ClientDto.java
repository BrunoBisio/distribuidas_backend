package distribuidas.backend.dtos;

import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClientDto {
    // user
    private String name;
    private String identityNumber;
    private String address;
    private Status status;
    private Long picture;
    // client
    private String country;
    private Boolean admitted;
    private Category category;
    private String authorizedBy;

    // private Boolean financialBackground;
    // private Boolean legalBackground;
    // private Boolean riskBackground;
}
