package sectorservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sectorservice.entities.Sector;

@Repository
public interface SectorRepository extends MongoRepository<Sector, Integer>{

}
