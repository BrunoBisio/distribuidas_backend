package distribuidas.backend.mappers;

import distribuidas.backend.dtos.PaymentMethodDto;
import distribuidas.backend.models.PaymentMethod;

public class PaymentMethodMapper {
    public static PaymentMethodDto toDto(PaymentMethod pm) {
        PaymentMethodDto dto = new PaymentMethodDto();
        dto.setId(pm.getId());
        dto.setAccountNumber(pm.getAccountNumber());
        dto.setCardNumber(pm.getCardNumber());
        dto.setCurrency(pm.getCurrency());
        dto.setCvv(pm.getCvv());
        dto.setExpirationDate(pm.getExpirationDate());
        dto.setStatus(pm.getStatus());
        dto.setTipo(pm.getTipo());
        return dto;
    }

    public static PaymentMethod fromDto(PaymentMethodDto dto) {
        PaymentMethod pm = new PaymentMethod();
        pm.setAccountNumber(dto.getAccountNumber());
        pm.setCardNumber(dto.getCardNumber());
        pm.setCurrency(dto.getCurrency());
        pm.setCvv(dto.getCvv());
        pm.setExpirationDate(dto.getExpirationDate());
        pm.setStatus(dto.getStatus());
        pm.setTipo(dto.getTipo());
        return pm;
    }
}
