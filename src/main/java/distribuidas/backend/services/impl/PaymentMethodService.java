package distribuidas.backend.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.PaymentMethodDto;
import distribuidas.backend.mappers.PaymentMethodMapper;
import distribuidas.backend.repositories.PaymentMethodRepository;
import distribuidas.backend.services.IPaymentMethodService;

@Service
public class PaymentMethodService implements IPaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepo;

    @Override
    public List<PaymentMethodDto> getPaymentMethods(int clientId) {
        return paymentMethodRepo.findByClientId(clientId)
            .stream().map(PaymentMethodMapper::toDto).collect(Collectors.toList());
    }
}
