package distribuidas.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.PaymentMethodDto;
import distribuidas.backend.enums.Status;
import distribuidas.backend.mappers.PaymentMethodMapper;
import distribuidas.backend.models.Client;
import distribuidas.backend.models.PaymentMethod;
import distribuidas.backend.repositories.ClientRepository;
import distribuidas.backend.repositories.PaymentMethodRepository;
import distribuidas.backend.services.IPaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepo;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<PaymentMethodDto> getPaymentMethods(int clientId) {
        return paymentMethodRepo.findByClientIdAndStatus(clientId,Status.activo)
            .stream().map(PaymentMethodMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PaymentMethodDto createPaymentMethod(int clientId, PaymentMethodDto dto) {
        Client client = clientRepository.findById(clientId).get();
        PaymentMethod pm = PaymentMethodMapper.fromDto(dto);
        pm.setClient(client);
        pm.setStatus(Status.inactivo);
        pm = paymentMethodRepo.save(pm);
        return PaymentMethodMapper.toDto(pm);
    }

    @Override
    public void deletePaymentMethod(int id) {
        PaymentMethod pm = paymentMethodRepo.findById(id).get();
        paymentMethodRepo.delete(pm);
    }
}
