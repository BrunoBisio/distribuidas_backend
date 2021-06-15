package distribuidas.backend.services;

import java.util.List;

import distribuidas.backend.dtos.PaymentMethodDto;

public interface IPaymentMethodService {
    List<PaymentMethodDto> getPaymentMethods(int clientId);

    PaymentMethodDto createPaymentMethod(int principalId, PaymentMethodDto dto);

    void deletePaymentMethod(int id);
}
