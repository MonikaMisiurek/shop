package pl.misiurek.shop.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/users")
public class UserUserViewController {


    @GetMapping
    public String account() {
        return "shop/userPage";
    }

}


