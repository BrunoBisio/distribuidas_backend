package distribuidas.backend.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import distribuidas.backend.dtos.AuctionsWonDto;
import distribuidas.backend.dtos.BidsCreatedDto;
import distribuidas.backend.dtos.ParticipatedAuctionDto;
import distribuidas.backend.dtos.PublishedProductsDto;
import distribuidas.backend.security.Context;
import distribuidas.backend.services.ISummaryService;

@RestController
@RequestMapping("stats")
public class SummaryController {
    @Autowired
    private ISummaryService service;
    
    // subastas en las que participo
    @GetMapping("/auctions/assisted")
    public long getAuctionedAuctions() { return service.getAuctionedAuctions(Context.getPrincipalId()); }
    
    @GetMapping("/auctions/assisted/details")
    public List<ParticipatedAuctionDto> getAuctionedAuctionsDetails() { return service.getAuctionedAuctionsDetails(Context.getPrincipalId()); }
    
    @GetMapping("/auctions/assisted/details/export")
    public void exportAuctionedAuctionsDetails(HttpServletResponse response) throws IOException { 
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=subastasParticipadas_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
         
        List<ParticipatedAuctionDto> auctions = service.getAuctionedAuctionsDetails(Context.getPrincipalId());
 
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Fecha", "Categoria", "Moneda", "Subasta"};
        String[] nameMapping = {"auctionDate", "category", "currency", "auctionName"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (ParticipatedAuctionDto auction : auctions) {
            csvWriter.write(auction, nameMapping);
        }
         
        csvWriter.close();
    }

    // subastas en las que gano
    @GetMapping("/auctions/won")
    public long getAuctionsWon() { return service.getAuctionsWon(Context.getPrincipalId()); }

    @GetMapping("/auctions/won/details")
    public List<AuctionsWonDto> getAuctionsWonDetails() { return service.getAuctionsWonDetails(Context.getPrincipalId()); }

    // subastas en las que oferto
    @GetMapping("/bids")
    public long getBidsCreated() { return service.getBidsCreated(Context.getPrincipalId()); }

    @GetMapping("/bids/details")
    public List<BidsCreatedDto> getBidsCreatedDetails() { return service.getBidsCreatedDetails(Context.getPrincipalId()); }
    
    // subastas en las que publico
    @GetMapping("/products/published")
    public long getProductsPublished() { return service.getProductsPublished(Context.getPrincipalId()); }

    @GetMapping("/products/published/details")
    public List<PublishedProductsDto> getProductsPublishedDetails() { return service.getProductsPublishedDetails(Context.getPrincipalId()); }
}
