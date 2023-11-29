package shop.springbootapp.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import shop.springbootapp.model.dto.CartProductDTO;
import shop.springbootapp.model.dto.ProductDTO;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.model.mapper.ProductMapper;
import shop.springbootapp.service.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class SessionCartUtil {

    private static final String CART_PRODUCTS = "cartProducts";
    private final ProductMapper productMapper;

    public SessionCartUtil(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<CartProductDTO> getAllProducts(HttpServletRequest request) {
        List<CartProductDTO> cartProducts = (List<CartProductDTO>) request.getSession().getAttribute(CART_PRODUCTS);
        if (Objects.isNull(cartProducts)) {
            request.getSession().setAttribute(CART_PRODUCTS, new ArrayList<>());
        }
        cartProducts = (List<CartProductDTO>) request.getSession().getAttribute(CART_PRODUCTS);
        return cartProducts;
    }

    public CartProductDTO getProductById(HttpServletRequest request, UUID id) {
        List<CartProductDTO> allSessionProducts = getAllProducts(request);
        return allSessionProducts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public String addProduct(HttpServletRequest request, Product product) {
        if (Objects.isNull(product) || Objects.isNull(product.getId())) {
            throw new ProductNotFoundException("Product not found !");
        }

        ProductDTO productDto = productMapper.toDto(product);

        CartProductDTO existingCartProduct = getProductById(request, UUID.fromString(productDto.getId()));
        if (Objects.isNull(existingCartProduct)) {
            getAllProducts(request).add(new CartProductDTO(UUID.fromString(productDto.getId()), productDto, 1));
        } else {
            existingCartProduct.increaseQuantity();
        }

        return productDto.getId().toString();
    }

    public String deleteProduct(HttpServletRequest request, String id) {

        //TODO
        return null;
    }

}
