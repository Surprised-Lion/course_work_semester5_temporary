package org.example.dao;

import org.example.entities.User;


public interface UserDao extends AbstractDao<User> {
    User getByUsername(String username);
}
