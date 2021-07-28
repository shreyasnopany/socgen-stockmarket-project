package companyservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import companyservice.entities.StockPrice;

@Repository
public interface StockPriceRepository extends MongoRepository<StockPrice,Integer> {
}