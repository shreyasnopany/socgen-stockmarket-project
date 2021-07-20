package companyservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import companyservice.entities.Company;

@Repository
public interface CompanyRepository extends MongoRepository<Company,Integer> {
}