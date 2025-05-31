package com.example.javaspringbootdemoprojectbackend.service.impl;

import com.example.javaspringbootdemoprojectbackend.entity.Users;
import com.example.javaspringbootdemoprojectbackend.model.dto.UsersDTO;
import com.example.javaspringbootdemoprojectbackend.repository.UsersRepository;
import com.example.javaspringbootdemoprojectbackend.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String addUser(UsersDTO usersDTO) {
        Users user = new Users();
        user.setName(usersDTO.getName());
        user.setEmail(usersDTO.getEmail());
        usersRepository.save(user);
        return "Usuário adicionado com ID: " + user.getId();
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAllByEnabledTrue();
    }

    @Override
    public Users getUserById(Long id) {
        return usersRepository.findByIdAndEnabledTrue(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado ou está desabilitado"));
    }

    @Override
    public String updateUser(Long id, UsersDTO usersDTO) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setName(usersDTO.getName());
        user.setEmail(usersDTO.getEmail());
        usersRepository.save(user);
        return "Usuário atualizado com sucesso";
    }

    @Override
    public String disableUser(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setEnabled(false);
        usersRepository.save(user);
        return "Usuário desabilitado com sucesso";
    }
}
