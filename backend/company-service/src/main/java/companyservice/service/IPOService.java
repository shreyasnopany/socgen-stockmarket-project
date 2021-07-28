package companyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import companyservice.entities.IPO;
import companyservice.repository.IPORepository;

@Service
public class IPOService {

    @Autowired
    public IPORepository ipoRepository;

    public String saveIPO(IPO ipo) {
        ipoRepository.save(ipo);
        return "Action Completed";
    }
}