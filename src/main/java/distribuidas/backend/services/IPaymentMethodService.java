package distribuidas.backend.services;

import java.util.List;

import distribuidas.backend.dtos.PaymentMethodDto;

public interface IPaymentMethodService {
    List<PaymentMethodDto> getPaymentMethods(int clientId);
}
