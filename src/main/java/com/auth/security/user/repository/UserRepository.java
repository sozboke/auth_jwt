package com.auth.security.user.repository;

import java.util.Optional;

import com.auth.security.user.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
