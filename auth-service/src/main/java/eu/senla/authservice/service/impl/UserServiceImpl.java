package eu.senla.authservice.service.impl;

import eu.senla.authservice.client.UserClient;
import eu.senla.authservice.dto.RegistrationRequest;
import eu.senla.authservice.dto.UserDTO;
import eu.senla.authservice.mapper.UserMapper;
import eu.senla.authservice.model.User;
import eu.senla.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserClient userClient;
    private final UserMapper userMapper;

    @Override
    public void regUser(RegistrationRequest registrationRequest) {
        userClient.createUser(registrationRequest);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.toEntity(userClient.getUserByEmail(email));
    }

    @Override
    public String getUserPasswordById(UUID id) {
        return userClient.getUserPasswordById(id);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userClient.getUserByEmail(email);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::findByEmail;
    }
}
