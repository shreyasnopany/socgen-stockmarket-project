package stockexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import stockexchange.repository.ExchangeRepository;

@SpringBootApplication(scanBasePackages={
        "stockexchange", "companyservice"})
@EnableMongoRepositories(basePackageClasses = ExchangeRepository.class)
public class ExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeApplication.class, args);
    }
}