package sectorservice.controller;

import companyservice.entities.Company;
import companyservice.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sectorservice.service.SectorService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class SectorController {

    private final SectorService sectorService;

    @Autowired
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping(value = "getSectorCompanies/{id}")
    public List<Company> getCompany(@PathVariable int id){
        return sectorService.getCompany(id);
    }

}
