/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mysite.service;

import com.mycompany.mysite.domain.User;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author WIN11
 */
public interface UserService {
    public void save(User u) throws Exception;
    public Page<User> findAll(String kw,Pageable pageable);
    public User findById(Integer uid);
    public void delete(User u);
    public void deleteById(Integer uid);
    public void deletes(List<User> users);
}
