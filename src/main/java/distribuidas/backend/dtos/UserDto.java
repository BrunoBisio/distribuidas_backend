package distribuidas.backend.dtos;

import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter @Setter
public class UserDto {
    // user
    private int id;
    private String email;
    private String password;
    private String document;
    private String name;
    private String address;
}
