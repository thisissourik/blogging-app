package com.thisissourik.blog.repositories;

import java.util.Optional;
import com.thisissourik.blog.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}
