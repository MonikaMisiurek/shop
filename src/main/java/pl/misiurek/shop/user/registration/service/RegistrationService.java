package pl.misiurek.shop.user.registration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.misiurek.shop.user.model.AppUser;
import pl.misiurek.shop.user.model.role.Role;
import pl.misiurek.shop.user.registration.EmailValidator;
import pl.misiurek.shop.user.registration.RegistrationRequest;
import pl.misiurek.shop.user.service.UserService;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalArgumentException("email not valid");
        }
        return userService.singUpUser(
                new AppUser(
                        request.getUsername(),
                        request.getPassword(),
                        request.getEmail(),
                        Role.USER

                )
        );
    }
}
