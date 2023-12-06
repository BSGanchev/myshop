package shop.springbootapp.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.springbootapp.model.dto.ProductDTO;
import shop.springbootapp.model.entity.Order;
import shop.springbootapp.model.entity.Picture;
import shop.springbootapp.service.OrderService;
import shop.springbootapp.service.ProductService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public String addProduct(@ModelAttribute("addProductDTO") ProductDTO productDTO){

        return "/product-add";
    }

    @PostMapping(path = "/product-add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addProductConfirm(@ModelAttribute @Valid ProductDTO productDTO,
                                                    @RequestParam("picture") MultipartFile file,
                                                    HttpServletRequest request) throws IOException {

        Picture picture = createPictureFromMultipart(file);
        request.getSession().removeAttribute("picture");

        this.productService.addProduct(productDTO, picture);

        return "redirect:product-add";

    }
    @GetMapping(path = "/product-edit", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String editProduct(@ModelAttribute @Valid ProductDTO productDTO,
                                    @RequestParam("picture") MultipartFile file,
                                    HttpServletRequest request) throws IOException {

        Picture picture = createPictureFromMultipart(file);
        request.getSession().removeAttribute("picture");

        this.productService.addProduct(productDTO, picture);

        return "redirect:product-add";

    }

    @GetMapping("/product-owner")
    public String productList(Model model){
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDTO> productDTOList = this.productService.getALlProducts().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
        if (!model.containsAttribute("products")) {
            model.addAttribute("products", productDTOList);
        }

        return "products-owner";

    }

    @GetMapping("/product-edit/{id}")
    public String productEdit(@PathVariable UUID id, Model model){
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO product = modelMapper.map(this.productService.getProductById(id), ProductDTO.class);
        if (!model.containsAttribute("product")) {
            model.addAttribute("product", product);
        }

        return "product-edit";

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
