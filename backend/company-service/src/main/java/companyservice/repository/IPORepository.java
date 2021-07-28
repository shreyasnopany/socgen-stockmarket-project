package companyservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import companyservice.entities.IPO;

@Repository
public interface IPORepository extends MongoRepository<IPO,Integer> {
}