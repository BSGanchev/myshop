package shop.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.springbootapp.model.entity.AppUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update AppUser u set u.lastLogged = now() where u.username = ?1")
    void setLastLoginTime(String username);

    @Query(value = "SELECT * FROM pofb.users AS u ORDER BY u.last_logged DESC LIMIT 20", nativeQuery = true)
    List<AppUser> findAllLLoggedIn();

    @Modifying
    @Transactional
    @Query("UPDATE AppUser u SET u.disabled = FALSE WHERE u.id = :id")
    void activateUser(@Param("id") UUID id);
}
