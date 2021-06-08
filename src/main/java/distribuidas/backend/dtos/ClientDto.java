package distribuidas.backend.dtos;

import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ClientDto {
    // user
    private String name;
    private String identityNumber;
    private String address;
    private Status status;
    private String picture;
    private String email;
    private String phoneNumber;
    // client
    private Category category;
}
