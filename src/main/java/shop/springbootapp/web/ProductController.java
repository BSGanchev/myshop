package shop.springbootapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/owner")
    public String owner() {
        return "admin-page";
    }

}
