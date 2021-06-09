package distribuidas.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.backend.dtos.PaymentMethodDto;
import distribuidas.backend.security.Context;
import distribuidas.backend.services.IPaymentMethodService;

@RestController
@RequestMapping("paymentMethod")
public class PaymentMethodController {
    @Autowired
    private IPaymentMethodService service;

    @GetMapping("")
    public List<PaymentMethodDto> index() {
        // Context.getPrincipalId()
        return service.getPaymentMethods(Context.getPrincipalId());
    }
}