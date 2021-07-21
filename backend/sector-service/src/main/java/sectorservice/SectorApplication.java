package sectorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sectorservice.repository.SectorRepository;

@SpringBootApplication(scanBasePackages={
        "sectorservice", "companyservice"
}
)

@EnableMongoRepositories(basePackageClasses = SectorRepository.class)
public class SectorApplication {
    public static void main(String[] args){
        SpringApplication.run(SectorApplication.class, args);
    }
}
