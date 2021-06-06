package distribuidas.backend.security;

import distribuidas.backend.dtos.UserDto;
import org.springframework.security.core.context.SecurityContextHolder;

public class Context {
    public static int getPrincipalId() {
        UserDto dto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return dto.getId();
    }
}
