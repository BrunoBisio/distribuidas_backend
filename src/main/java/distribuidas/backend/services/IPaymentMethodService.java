package distribuidas.backend.services;

import java.util.List;

import distribuidas.backend.dtos.PaymentMethodDto;

public interface IPaymentMethodService {
    public List<PaymentMethodDto> getPaymentMethods(int clientId);
    public PaymentMethodDto createPaymentMethod(int principalId, PaymentMethodDto dto);
    public void deletePaymentMethod(int id);
}
