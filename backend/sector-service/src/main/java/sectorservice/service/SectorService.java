package sectorservice.service;

import companyservice.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService {

    @Autowired
    public MongoTemplate mongoTemplate;

    public List<Company> getCompany(int id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("sector_id").is(id));
        return mongoTemplate.find(query,Company.class);
    }
}
