package distribuidas.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.security.Context;
import distribuidas.backend.services.IProductService;

@RestController
@RequestMapping("item")
public class ProductController {
    
    @Autowired
    private IProductService productService;

    @GetMapping("/sold")
    public List<ProductDto> getSoldProducts() { return productService.getSoldProducts(Context.getPrincipalId()); }

    @GetMapping("/auction/active")
    public List <ProductDto> getActiveAuctionProducts() { return productService.getActiveAuctionProducts(Context.getPrincipalId()); }
    
    @GetMapping("/auction/soon")
    public List <ProductDto> getPendingAuctionProducts() { return productService.getPendingAuctionProducts(Context.getPrincipalId()); }
    
    @GetMapping("/unapproved")
    public List<ProductDto> getUnapprovedProducts() { return productService.getUnapprovedProducts(Context.getPrincipalId()); }
}
