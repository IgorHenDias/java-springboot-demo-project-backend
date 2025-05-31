package com.example.javaspringbootdemoprojectbackend.controller;

import com.example.javaspringbootdemoprojectbackend.model.dto.UsersDTO;
import com.example.javaspringbootdemoprojectbackend.entity.Users;
import com.example.javaspringbootdemoprojectbackend.service.UsersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public String addUser(@RequestBody UsersDTO usersDTO) {
        return usersService.addUser(usersDTO);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    // Correto: @PathVariable com nome explícito (opcional, mas recomendado para clareza)
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable("id") Long id) {
        return usersService.getUserById(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody UsersDTO usersDTO) {
        return usersService.updateUser(id, usersDTO);
    }

    @DeleteMapping("/{id}")
    public String disableUser(@PathVariable("id") Long id) {
        return usersService.disableUser(id);
    }
}