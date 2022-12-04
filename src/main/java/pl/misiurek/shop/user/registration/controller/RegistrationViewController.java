package pl.misiurek.shop.user.registration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.misiurek.shop.user.model.AppUser;
import pl.misiurek.shop.user.registration.RegistrationRequest;
import pl.misiurek.shop.user.registration.service.RegistrationService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/users")
public class RegistrationViewController {

    private final RegistrationService registrationService;


    @GetMapping("register")
    public String registerView(Model model) {
        model.addAttribute("registrationRequest", new AppUser());
        return "shop/register";
    }


    @PostMapping
    public String register(RegistrationRequest request) {
        registrationService.register(request);
        return "redirect:/shop";
    }

}
