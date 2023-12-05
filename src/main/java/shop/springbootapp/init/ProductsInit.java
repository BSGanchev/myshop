package shop.springbootapp.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shop.springbootapp.service.ProductService;
import shop.springbootapp.service.ProductTypeService;

@Component
public class ProductsInit implements CommandLineRunner {
    private final ProductTypeService productTypeService;
    private final ProductService productService;

    public ProductsInit(ProductTypeService productTypeService, ProductService productService) {
        this.productTypeService = productTypeService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.productTypeService.initProductTypes();

    }
}
