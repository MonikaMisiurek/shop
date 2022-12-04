package pl.misiurek.shop.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.misiurek.shop.user.model.AppUser;
import pl.misiurek.shop.user.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final String USER_NOT_FOUND = "user with email %s not found";

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public Page<AppUser> getUsers(Pageable pageable) {
        return getUsers(pageable, null);
    }

    @Transactional(readOnly = true)
    public Page<AppUser> getUsers(Pageable pageable, String search) {
        if (search == null) {
            return userRepository.findAll(pageable);
        } else {
            return userRepository.findByEmailContainingIgnoreCase(search, pageable);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String singUpUser(AppUser appUser) {
        boolean userExist = userRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userExist) {
            throw new IllegalArgumentException("email exist");
        }
        String encodePassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodePassword);

        userRepository.save(appUser);
        return "";
    }

    public AppUser getUser(UUID id) {
        return userRepository.findById(id).get();
    }
}
