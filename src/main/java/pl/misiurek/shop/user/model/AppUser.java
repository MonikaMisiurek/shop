package pl.misiurek.shop.user.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.misiurek.shop.user.model.role.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;


@Data
@EqualsAndHashCode
@Entity(name = "users")
public class AppUser implements UserDetails {


    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private UUID id;

    @NotBlank(message = " Name can't be empty")
    private String username;

    @NotBlank(message = "Pleas insert the password")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Pleas insert the email")
    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public AppUser(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public AppUser() {
        this.id = UUID.randomUUID();
        this.role = Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(authority);
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
