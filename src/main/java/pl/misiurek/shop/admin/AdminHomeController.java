package pl.misiurek.shop.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/home")
public class AdminHomeController {

    @GetMapping
    public String homeAdminView() {
        return "admin/index";
    }
}
