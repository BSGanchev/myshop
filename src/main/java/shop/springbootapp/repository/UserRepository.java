package shop.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.springbootapp.model.entity.AppUser;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {

    Optional<AppUser> findByEmail(String email);

}
