package distribuidas.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    // user
    private int id;
    private String email;
    private String password;
    private String document;
    private String name;
    private String address;
    private String phoneNumber;
}
