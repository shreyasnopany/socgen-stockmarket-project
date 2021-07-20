package companyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import companyservice.repository.CompanyRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = CompanyRepository.class)
public class CompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class, args);
    }
}