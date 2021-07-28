package stockexchange.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import stockexchange.entity.Exchange;


@Repository
public interface ExchangeRepository extends MongoRepository<Exchange,Integer> {

}