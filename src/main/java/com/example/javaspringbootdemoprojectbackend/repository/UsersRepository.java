package com.example.javaspringbootdemoprojectbackend.repository;

import com.example.javaspringbootdemoprojectbackend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
    Optional<Users> findByIdAndEnabledTrue(Long id); // <-- Adicione isso

    List<Users> findAllByEnabledTrue();
}
