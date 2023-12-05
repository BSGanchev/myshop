package shop.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.springbootapp.model.entity.Picture;

import java.util.UUID;
@Repository
public interface PictureRepository extends JpaRepository<Picture, UUID> {
}
