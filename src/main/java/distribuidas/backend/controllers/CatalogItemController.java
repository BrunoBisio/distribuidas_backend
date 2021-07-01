package distribuidas.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.models.CatalogItem;
import distribuidas.backend.services.ICatalogItemService;

@RestController
@RequestMapping("")
public class CatalogItemController {
    @Autowired
    private ICatalogItemService service;
    
    @GetMapping("/auction/{id}/item")
    public ProductDto getByAuctionId(@PathVariable int id) throws Exception {
        return service.getByAuctionId(id);
    }

    @GetMapping("/catalogItem/{id}")
    public CatalogItem getCatalogItem(@PathVariable int id) {
        return service.getById(id);
    }
}
