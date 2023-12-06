package shop.springbootapp.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import shop.springbootapp.model.dto.AddProductDTO;
import shop.springbootapp.model.entity.Order;
import shop.springbootapp.model.entity.Picture;
import shop.springbootapp.model.entity.Product;
import shop.springbootapp.model.entity.ProductDTO;
import shop.springbootapp.repository.PictureRepository;
import shop.springbootapp.service.OrderService;
import shop.springbootapp.service.ProductService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    private final OrderService orderService;
    private final ProductService productService;
    @Autowired
    PictureRepository pictureRepository;

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

    @RequestMapping(path = "/product-add", method = POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addProductConfirm(@ModelAttribute @Valid AddProductDTO addProductDTO,
                                                    @RequestParam("picture") MultipartFile file,
                                                    HttpServletRequest request) throws IOException {


        Picture picture = createPictureFromMultipart(file);
        request.getSession().removeAttribute("picture");


        addProductDTO.getType();

        this.productService.addProduct(addProductDTO, picture);

        return "redirect:product-add";

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
