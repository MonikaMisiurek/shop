package pl.misiurek.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.misiurek.shop.admin.category.service.CategoryService;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;

    @GetMapping
    public String homeView(Model model) {
        model.addAttribute("categories", categoryService.getCategories(Pageable.unpaged()));
        return "shop/home";
    }
}
