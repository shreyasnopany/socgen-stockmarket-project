package companyservice.controller;

import org.springframework.web.bind.annotation.*;
import companyservice.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import companyservice.repository.CompanyRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping(value = "/company")
    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }

    @PostMapping(value = "/company")
    public String saveCompany(@RequestBody Company company){
        companyRepository.save(company);
        return "Action Completed";
    }
}