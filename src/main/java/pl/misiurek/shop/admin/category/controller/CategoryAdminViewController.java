package pl.misiurek.shop.admin.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.misiurek.shop.admin.category.domian.model.Category;
import pl.misiurek.shop.admin.category.service.CategoryService;
import pl.misiurek.shop.common.dto.Message;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryAdminViewController {

    private final CategoryService categoryService;


    @GetMapping
    public String indexView(
            Pageable pageable,
            @RequestParam(name = "s", required = false) String search,
            Model model) {
        Page<Category> categoryPage = categoryService.getCategories(pageable, search);
        model.addAttribute("categoriesPage", categoryPage);
        model.addAttribute("search", search);
        paging(model, categoryPage);
        return "admin/category/index";
    }

    @GetMapping("{id}")
    public String editView(Model model, @PathVariable UUID id) {
        model.addAttribute("category", categoryService.getCategory(id));
        return "admin/category/edit";
    }

    @PostMapping("{id}")
    public String edit(
            @Valid @ModelAttribute("category") Category category,
            BindingResult bindingResult,
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes,
            Model model

    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("message", Message.error("Błąd zapisu"));
            return "admin/category/edit";
        }
        try {
            categoryService.updateCategory(id, category);
            redirectAttributes.addFlashAttribute("message", Message.info("Kategoria zapisana"));

        } catch (Exception e) {
            model.addAttribute("category", category);
            model.addAttribute("message", Message.error("Nieznany zapis"));

            return "admin/category/edit";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("add")
    public String addView(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/add";
    }

    @PostMapping
    public String add(@Valid Category category,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("message", Message.error("Błąd zapisu"));
            return "admin/category/add";
        }
        try {
            categoryService.createCategory(category);
            redirectAttributes.addFlashAttribute("message", Message.info("Kategoria zapisana"));

        } catch (Exception e) {
            model.addAttribute("category", category);
            model.addAttribute("message", Message.error("Nieznany zapis"));

            return "admin/category/add";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";

    }


    private void paging(Model model, Page page) {
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

}
