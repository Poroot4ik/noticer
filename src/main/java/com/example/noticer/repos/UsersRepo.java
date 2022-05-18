package com.example.noticer.repos;

import com.example.noticer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
