package distribuidas.backend.mappers;

import distribuidas.backend.dtos.PaymentMethodDto;
import distribuidas.backend.models.PaymentMethod;

public class PaymentMethodMapper {
    public static PaymentMethodDto toDto(PaymentMethod pm) {
        PaymentMethodDto dto = new PaymentMethodDto();
        dto.setAccountNumber(pm.getAccountNumber());
        dto.setCardNumber(pm.getCardNumber());
        dto.setCurrency(pm.getCurrency());
        dto.setCvv(pm.getCvv());
        dto.setExpirationDate(pm.getExpirationDate());
        dto.setStatus(pm.getStatus());
        dto.setTipo(pm.getTipo());
        return dto;
    }
}
