package com.shopping.service;

import com.shopping.model.Product;
import com.shopping.model.User;

import java.util.List;

/**
 * @Author Seyed Mohsen Hosseini
 * @Since 31 July 2021
 */
public interface UserService {

    void save(User user);
    void login(String username, String password);
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);
    List<User> findAll();
    void edit(long id, boolean blocked);
}
