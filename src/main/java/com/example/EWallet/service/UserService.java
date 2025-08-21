package com.example.EWallet.service;

import com.example.EWallet.DTO.UserRegistrationDTO;
import com.example.EWallet.DTO.UserResponseDTO;
import com.example.EWallet.entities.User;
import com.example.EWallet.errorhandler.exceptions.UserNotFoundException;
import com.example.EWallet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public UserResponseDTO RegisterUser(UserRegistrationDTO dto) {
        User user=new User();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstname());
        user.setLastName(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User saved=userRepository.save(user);
        return new UserResponseDTO(saved.getId(),saved.getUsername(),saved.getEmail(),saved.getFirstName(), saved.getLastName());

    }

    public UserResponseDTO getUserById(UUID id) {
        Optional<User> user= Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));

        return new UserResponseDTO( user.get().getId(),
                                    user.get().getUsername(),
                                    user.get().getEmail(),
                                    user.get().getFirstName(),
                                    user.get().getLastName()
                                  );
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserResponseDTO( u.getId(),
                                                    u.getUsername(),
                                                    u.getEmail(),
                                                    u.getFirstName(),
                                                    u.getLastName()))
                .collect(Collectors.toList());
    }
}
