package distribuidas.backend.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.mappers.ProductMapper;
import distribuidas.backend.models.CatalogItem;
import distribuidas.backend.models.Client;
import distribuidas.backend.models.Owner;
import distribuidas.backend.models.Product;
import distribuidas.backend.repositories.CatalogItemRepository;
import distribuidas.backend.repositories.ClientRepository;
import distribuidas.backend.repositories.EmployeeRepository;
import distribuidas.backend.repositories.OwnerRepository;
import distribuidas.backend.repositories.ProductRepository;
import distribuidas.backend.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository prodRepository;
    @Autowired
    private CatalogItemRepository ciRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ProductDto> getSoldProducts(int clientId) {
        return ciRepository.findByProductOwnerIdAndAuctioned(clientId, Admited.si).stream()
            .map(CatalogItem::getProduct).map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getActiveAuctionProducts(int clientId) {
        return prodRepository.findActiveAuctionProducts(clientId).stream()
            .map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getPendingAuctionProducts(int clientId) {
        return prodRepository.findPendingAuctionProducts(clientId).stream()
            .map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getUnapprovedProducts(int clientId) {
        return prodRepository.findByOwnerIdAndApproved(clientId, Admited.no).stream()
            .map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(int clientId, ProductDto product) {
        Product p = new Product();
        p.setCreated(new Date());
        p.setAvailable(Admited.no);
        p.setFullDescription(product.getDescription());
        p.setEmployee(employeeRepository.findById(3).get());
        Owner owner = ownerRepository.findById(clientId).get();
        if (owner == null) {
            owner = createOwner(clientId);
        }
        p.setOwner(owner);
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setCurrency("ARS");
        p.setApproved(Admited.no);
        p = prodRepository.save(p);
        return ProductMapper.toDto(p);
    }

    private Owner createOwner(int clientId) {
        Client client = clientRepository.findById(clientId).get();
        Owner owner = new Owner();
        owner.setId(clientId);
        owner.setCountry(client.getCountry());
        owner.setFinancialVerification(Admited.no);
        owner.setLegalVerification(Admited.no);
        owner.setRiskCalification(6);
        owner.setEmployee(employeeRepository.findById(3).get());
        return ownerRepository.save(owner);
    }

    @Override
    public boolean deleteProduct(int clientId, int id) {
        Product prod = prodRepository.findByIdAndOwnerId(id, clientId);
        // si el producto ya a sido aprovado no puede ser borrado.
        if (prod.getApproved().ordinal() == Admited.si.ordinal())
            return false;
        
        prodRepository.delete(prod);
        return true;
    }
    
}
