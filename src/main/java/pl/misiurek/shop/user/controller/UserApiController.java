package pl.misiurek.shop.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.misiurek.shop.user.model.AppUser;
import pl.misiurek.shop.user.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserApiController {

    private final UserService userService;


    @GetMapping
    Page<AppUser> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @GetMapping("{id}")
    public AppUser accountView(@PathVariable UUID id) {
        return userService.getUser(id);
    }

}
