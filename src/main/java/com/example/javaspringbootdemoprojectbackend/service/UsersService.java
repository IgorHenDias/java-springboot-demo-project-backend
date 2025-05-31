package com.example.javaspringbootdemoprojectbackend.service;

import com.example.javaspringbootdemoprojectbackend.entity.Users;
import com.example.javaspringbootdemoprojectbackend.model.dto.UsersDTO;

import java.util.List;

public interface UsersService {
    String addUser(UsersDTO usersDTO);
    List<Users> getAllUsers();
    Users getUserById(Long id);
    String updateUser(Long id, UsersDTO usersDTO);
    String disableUser(Long id);
}