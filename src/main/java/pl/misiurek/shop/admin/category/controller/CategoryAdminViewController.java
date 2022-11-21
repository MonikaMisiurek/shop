package pl.misiurek.shop.admin.category.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.misiurek.shop.admin.category.domian.model.Category;
import pl.misiurek.shop.admin.category.service.CategoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminViewController {

    private final CategoryService categoryService;

    public CategoryAdminViewController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public String indexView(
            Pageable pageable,
            @RequestParam(name = "s", required = false) String search,
            Model model){
        Page<Category>categoryPage = categoryService.getCategories(pageable, search);
        model.addAttribute("categoriesPage", categoryPage);
        model.addAttribute("search", search);
        paging(model, categoryPage);
        return "admin/category/index";
//        return "admin/template";
    }

    @GetMapping("{id}")
    public String editView(Model model, @PathVariable UUID id){
        model.addAttribute("category", categoryService.getCategory(id));
        return "admin/category/edit";
//        return  "admin/template";
    }

    @PostMapping("{id}")
    public String edit(@ModelAttribute("category")Category category,@PathVariable UUID id){ // @ModelAttribute - połączenie informacji z widoku
        categoryService.updateCategory(id,category);
        return "redirect:/admin/categories";
    }
    @GetMapping("{id}/delete")
    public String deleteView(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";

    }
    @GetMapping("add")
    public String addView(Model model){
        model.addAttribute("category", new Category());

        return "admin/category/add";
    }

    @PostMapping
    public String add(Category category){
        categoryService.createCategory(category);
        return "redirect:/admin/categories";
    }


    private void paging(Model model, Page page){
        int totalPages = page.getTotalPages();
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

}
