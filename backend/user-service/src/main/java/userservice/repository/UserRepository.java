package userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import userservice.entity.AppUser;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends MongoRepository<AppUser, Long> {

    @Query("Select s From User s where s.username = ?1")
    Optional<AppUser> findByUsername(String username);

    @Query("Select s From User s where s.email = ?1")
    Optional<AppUser> findByEmail(String email);

}