package pl.misiurek.shop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.misiurek.shop.user.model.AppUser;
import pl.misiurek.shop.user.model.role.Role;
import pl.misiurek.shop.user.repository.UserRepository;

@Component
@RequiredArgsConstructor

public class DbInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(final String... args) throws Exception {
        userRepository.save(new AppUser("ADMIN", passwordEncoder.encode("admin"), "a@a", Role.ADMIN));
        userRepository.save(new AppUser("user", passwordEncoder.encode("user"), "u@u", Role.USER));
        userRepository.save(new AppUser("SUPER_ADMIN", passwordEncoder.encode("SUPER_ADMIN"), "s@s", Role.SUPER_ADMIN));
        userRepository.save(new AppUser("MANAGER", passwordEncoder.encode("MANAGER"), "m@m", Role.MANAGER));

    }

}
