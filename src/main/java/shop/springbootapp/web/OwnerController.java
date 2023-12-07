package shop.springbootapp.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.springbootapp.model.dto.AddProductDTO;
import shop.springbootapp.model.entity.Order;
import shop.springbootapp.model.entity.Picture;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.service.OrderService;
import shop.springbootapp.service.ProductService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final OrderService orderService;
    private final ProductService productService;


    public OwnerController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/orders")
    public String ownerPage(Model model) {
        List<Order> allOrders = this.orderService.getAllOrders();

        if (!model.containsAttribute("orders")) {
            model.addAttribute("orders", allOrders);
        }

            System.out.println(this.orderService.getAllOrders());
        return "orders";
    }
    @GetMapping("/product-add")
    public String addProduct(@ModelAttribute("addProductDTO") AddProductDTO addProductDTO){

        return "/product-add";
    }

    @PostMapping(path = "/product-add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addProductConfirm(@ModelAttribute @Valid AddProductDTO addProductDTO,
                                                    @RequestParam("picture") MultipartFile file,
                                                    HttpServletRequest request) throws IOException {

        Picture picture = createPictureFromMultipart(file);
        request.getSession().removeAttribute("picture");

        this.productService.addProduct(addProductDTO, picture);

        return "redirect:product-add";

    }
    @GetMapping(path = "/product-add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String editProduct(@ModelAttribute @Valid AddProductDTO addProductDTO,
                                    @RequestParam("picture") MultipartFile file,
                                    HttpServletRequest request) throws IOException {

        Picture picture = createPictureFromMultipart(file);
        request.getSession().removeAttribute("picture");

        this.productService.addProduct(addProductDTO, picture);

        return "redirect:product-add";

    }
    @GetMapping("/products-all")
    public String showAllProduct(Model model){
        List<Product> products = this.productService.getALlProducts();
        if (!model.containsAttribute("products")) {
            model.addAttribute("products", products);
        }

        return "/products-all";
    }

    @GetMapping("/product-edit/{id}")
    public String editProduct(@PathVariable String id, Model model){
        Product productById = this.productService.getProductById(id);
        model.addAttribute("product", productById);

        return "/product-edit";
    }

    @PostMapping(path = "/product-edit/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String editProductConfirm(@PathVariable String id,
                                     @ModelAttribute ("product") AddProductDTO oldProduct,
                                     @RequestParam("picture") MultipartFile file,
                                     HttpServletRequest request) throws IOException {

        Picture picture = createPictureFromMultipart(file);
        request.getSession().removeAttribute("picture");

        System.out.println(oldProduct.getId());

        this.productService.updateProduct(oldProduct, picture, id);

        return "redirect:/owner/products-all";
    }


    private static Picture createPictureFromMultipart(MultipartFile file) throws IOException {
        Picture picture = new Picture();
        picture.setFilename(file.getOriginalFilename());
        picture.setContentType(file.getContentType());
        picture.setFilesize(Math.toIntExact(file.getSize()));
        picture.setContent(file.getBytes());
        picture.setCreatedDate(new Date());
        return picture;
    }

}
