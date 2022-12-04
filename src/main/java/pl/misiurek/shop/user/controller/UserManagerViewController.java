package pl.misiurek.shop.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.misiurek.shop.user.model.AppUser;
import pl.misiurek.shop.user.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor

@RequestMapping("/manager/users")
public class UserManagerViewController {

    private final UserService userService;


    @GetMapping("{id}")
    public String singleUser(@PathVariable UUID id, Model model) {
        AppUser appUser = userService.getUser(id);
        model.addAttribute("appUser", appUser);

        return "admin/user/singleUser";
    }

    @GetMapping
    public String userList(
            Pageable pageable,
            @RequestParam(name = "s", required = false) String search,
            Model model) {
        Page<AppUser> userPage = userService.getUsers(pageable, search);
        model.addAttribute("usersPage", userPage);
        model.addAttribute("search", search);
        paging(model, userPage);
        return "admin/user/userList";
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
