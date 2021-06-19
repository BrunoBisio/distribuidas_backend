package distribuidas.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return service.getPaymentMethods(Context.getPrincipalId());
    }

    @PostMapping("")
    public PaymentMethodDto index(@RequestBody PaymentMethodDto dto) {
        return service.createPaymentMethod(Context.getPrincipalId(), dto);
    }

    @DeleteMapping("/{id}")
    public void index(@PathVariable int id) {
        service.deletePaymentMethod(id);
    }
}