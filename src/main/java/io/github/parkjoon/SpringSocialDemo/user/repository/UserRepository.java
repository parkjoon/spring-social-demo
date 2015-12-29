package io.github.parkjoon.SpringSocialDemo.user.repository;

import io.github.parkjoon.SpringSocialDemo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
