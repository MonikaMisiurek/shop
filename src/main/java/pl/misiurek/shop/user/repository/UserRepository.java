package pl.misiurek.shop.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.misiurek.shop.user.model.AppUser;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {

    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findById(UUID id);

    Page<AppUser> findByEmailContainingIgnoreCase(String search, Pageable pageable);
}
