package distribuidas.backend.dtos;

import distribuidas.backend.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentMethodDto {
    private String tipo;
    private String currency;
    private String accountNumber;
    private String cardNumber;
    private String cvv;
    private String expirationDate;
    private Status status;
}
