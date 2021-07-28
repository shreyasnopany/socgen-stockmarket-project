package userservice.repository;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import userservice.entity.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);
}