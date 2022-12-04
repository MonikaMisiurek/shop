package pl.misiurek.shop.user.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.misiurek.shop.user.model.role.Role;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private String username;
    private String password;
    private String email;
    private Role role;

}
