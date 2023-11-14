package shop.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.springbootapp.model.entity.ProductType;
import shop.springbootapp.model.enums.ProductTypeEnum;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, UUID> {

    Optional<ProductType> findProductTypeByProductTypeName(ProductTypeEnum name);
}
