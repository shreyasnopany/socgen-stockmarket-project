package excelservice.repository;

import excelservice.entity.StockPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockPriceRepository extends MongoRepository<StockPrice, Long> {

}