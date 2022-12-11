package org.example.service;


import org.example.dto.UserDto;
import org.example.entities.Author;
import org.example.entities.Poem;
import org.example.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends AbstractService<User>, UserDetailsService {
    void login(String username, String password);

    void setAuthenticationManager(AuthenticationManager authenticationManager);

    User addUser(UserDto userDto);

    List<Author> getLikedAuthorsByUserId(Long userId);

    List<Poem> getLikedPoemsByUserId(Long userId);
}
