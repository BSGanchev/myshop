package shop.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shop.springbootapp.model.entity.UserActivationToken;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivationTokenRepository extends JpaRepository<UserActivationToken, UUID> {
    @Query(nativeQuery = true, value = "SELECT ual.user_id FROM user_activation_links AS ual WHERE activation_link = ?;")
    UUID getUserId(String activationLink);
    @Modifying
    @Query(nativeQuery = true, value = "SELECT * FROM user_activation_links WHERE datediff(now(), created) >= 1;")
    List<UserActivationToken> findExpiredToken();

    Optional<UserActivationToken> findByActivationToken(String token);
}
