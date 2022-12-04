package pl.misiurek.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop/cv")
public class CvController {

    @GetMapping
    public String cvView(){
        return "shop/cv";
    }

}
