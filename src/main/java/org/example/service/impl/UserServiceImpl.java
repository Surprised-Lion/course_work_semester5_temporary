package org.example.service.impl;


import org.example.dao.LikeDao;
import org.example.dao.RoleDao;
import org.example.dao.UserDao;
import org.example.dto.UserDto;
import org.example.entities.Author;
import org.example.entities.Poem;
import org.example.entities.Role;
import org.example.entities.User;

import org.example.exceptions.SecurityConfigurationException;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class UserServiceImpl extends AbstractServiceImpl<User, UserDao> implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private LikeDao likeDao;

    @Autowired
    private BCryptPasswordEncoder encoder;


    private AuthenticationManager authenticationManager;

    public UserServiceImpl(UserDao defaultDao) {
        super(defaultDao);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return defaultDao.getByUsername(username);
    }

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User addUser(UserDto userDto) {
        Role role = roleDao.getById(userDto.getRoleId());
        String pass = encoder.encode(userDto.getPassword());
        User user = new User(userDto.getUsername(), pass, userDto.getEmail(), userDto.getDateOfBirth(), userDto.getGenderType(), role);
        user = userDao.create(user);
        return user;
    }

    @Override
    public List<Author> getLikedAuthorsByUserId(Long userId) {
        return likeDao.getLikedAuthorsByUserId(userId);
    }

    @Override
    public List<Poem> getLikedPoemsByUserId(Long userId) {
        return likeDao.getLikedPoemsByUserId(userId);
    }

    @Override
    public void login(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        if (userDetails == null) {
            throw new EntityNotFoundException("User with username " + username + " doesn't exist");
        }
        if (encoder.matches(password, userDetails.getPassword())) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                return;
            }
        }
        throw new SecurityConfigurationException("Error during login");
    }
}
