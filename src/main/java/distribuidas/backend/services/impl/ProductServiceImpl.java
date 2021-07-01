package distribuidas.backend.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.enums.State;
import distribuidas.backend.mappers.ProductMapper;
import distribuidas.backend.models.AuctionRegistry;
import distribuidas.backend.models.CatalogItem;
import distribuidas.backend.models.Client;
import distribuidas.backend.models.Owner;
import distribuidas.backend.models.Photo;
import distribuidas.backend.models.Product;
import distribuidas.backend.repositories.AuctionRegistryRepository;
import distribuidas.backend.repositories.CatalogItemRepository;
import distribuidas.backend.repositories.ClientRepository;
import distribuidas.backend.repositories.EmployeeRepository;
import distribuidas.backend.repositories.OwnerRepository;
import distribuidas.backend.repositories.PhotoRepository;
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
    @Autowired
    private AuctionRegistryRepository arRepository;
    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<ProductDto> getSoldProducts(int clientId) {
        List<CatalogItem> cis = ciRepository.findByProductOwnerIdAndAuctioned(clientId, Admited.si);
        List<AuctionRegistry> ars = arRepository.findByOwnerIdAndProductIn(clientId, 
            cis.stream().map(CatalogItem::getProduct).collect(Collectors.toList()));
        return cis.stream().map((ci) -> {
            return ProductMapper.toSoldDto(getProductWithPrice(ci),
                ars.stream().filter((ar) -> { return ar.getProduct().getId() == ci.getProduct().getId(); }).findFirst().get() );
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getActiveAuctionProducts(int clientId) {
        return ciRepository.findByProductOwnerIdAndCatalogAuctionState(clientId, State.abierta).stream()
            .map(ProductServiceImpl::getProductWithPrice).map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getPendingAuctionProducts(int clientId) {
        Stream<ProductDto> itemsWithAuction = ciRepository.findPendingAuctionProducts(clientId).stream()
            .map(ProductServiceImpl::getProductWithPrice).map(ProductMapper::toPendingDto);
        Stream<ProductDto> itemsWithoutAuction = prodRepository.findByOwnerIdAndApproved(clientId, Admited.si).stream()
            .filter((p) -> {
                return !ciRepository.existsByProductId(p.getId());
            }).map(ProductMapper::toDeletableDto);
        return Stream.concat(itemsWithAuction, itemsWithoutAuction).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getUnapprovedProducts(int clientId) {
        return prodRepository.findByOwnerIdAndApproved(clientId, Admited.no).stream()
            .map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(int clientId, ProductDto product) {
        Product newProduct = new Product();
        newProduct.setCreated(new Date());
        newProduct.setAvailable(Admited.si);
        newProduct.setFullDescription(product.getFullDescription());
        newProduct.setCatalogDescription(product.getDescription());
        newProduct.setEmployee(employeeRepository.findById(3).get());
        if (ownerRepository.existsById(clientId)) {
            newProduct.setOwner(ownerRepository.findById(clientId).get());
        } else {
            newProduct.setOwner(createOwner(clientId));
        }
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setCurrency("ARS");
        newProduct.setApproved(Admited.no);
        newProduct = prodRepository.save(newProduct);
        List<Photo> photos = new ArrayList<>();
        for (String p : product.getPhotos()) {
            photos.add(new Photo(newProduct, p));
        }
        photoRepository.saveAll(photos);
        return ProductMapper.toDto(newProduct);
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
        // si el producto ya a sido se debe validar que no se haya agregado a alguna subasta para que pueda ser borrado.
        if (prod.getApproved().ordinal() == Admited.si.ordinal()) {
            if (ciRepository.existsByProductId(prod.getId())) {
                return false;
            }
        }
        
        prodRepository.delete(prod);
        return true;
    }

    @Override
    public ProductDto getProductById(int id) {
        Product prod = prodRepository.findById(id).get();
        if (prod != null)
            return ProductMapper.toDto(prod);
        return null;
    }

    public static Product getProductWithPrice(CatalogItem ci) {
        ci.getProduct().setPrice(ci.getBasePrice());
        return ci.getProduct();
    }

    
    
}
