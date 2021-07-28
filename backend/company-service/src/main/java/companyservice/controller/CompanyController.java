package companyservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import companyservice.dto.ResponseObject;
import companyservice.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import companyservice.entities.IPO;
import companyservice.entities.StockPrice;
import companyservice.repository.CompanyRepository;
import companyservice.repository.IPORepository;
import companyservice.service.CompanyService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private IPORepository ipoRepository;

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/company/{id}")
    public Optional<Company> getCompanyDetails(@PathVariable int id){
        return companyService.getCompanyDetails(id);
    }

    @PostMapping(value = "/company")
    public ResponseEntity<ResponseObject> saveCompany(@RequestBody Company company)
    {
        ResponseObject responseObject = new ResponseObject();
        try {
            companyService.saveCompany(company);
            responseObject.setStatus(true);
            responseObject.setMessage("Action Successful");
            return ResponseEntity.ok(responseObject);
        }
        catch (Exception e)
        {
            responseObject.setStatus(false);
            responseObject.setError("Invalid Input");
            return new ResponseEntity(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/companyIPO/{id}")
    public List<IPO> getCompanyIPODetails(@PathVariable int id){
        return companyService.getCompanyIPODetails(id);
    }

    @GetMapping(value = "{exchangeId}/{companyId}/{from_period}/{to_period}/{periodicity}")
    public List<StockPrice> getCompanyStockPrice(@PathVariable int exchangeId, @PathVariable String companyId,
                                                 @PathVariable LocalDate from_period, @PathVariable LocalTime to_period,
                                                 @PathVariable int periodicity){

        return companyService.getCompanyStockPrice(exchangeId,companyId,from_period,to_period,periodicity);

    }

    // This service to be made during frontend creation

}