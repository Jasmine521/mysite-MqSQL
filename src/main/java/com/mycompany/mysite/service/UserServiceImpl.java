/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mysite.service;

import com.mycompany.mysite.dao.UserRepository;
import com.mycompany.mysite.domain.User;

import java.time.Instant;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author WIN11
 */
@Service
public class UserServiceImpl implements UserService {
    Logger logger;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User u) throws Exception{
        try {
            u.setLasttime((int) Instant.now().getEpochSecond());
            userRepository.save(u);
        }catch (Exception e){
            logger.info("插入数据失败！{}",e.toString());
            logger.info(("aaa"));
            throw new Exception();
        }

    }

    @Override
    public Page<User> findAll(String kw, Pageable pageable) {
        return userRepository.findByKeyword(kw, pageable);
    }

    @Override
    public User findById(Integer uid) {
        return userRepository.findById(uid).get();
    }

    @Override
    public void delete(User u) {
        userRepository.delete(u);
    }

    @Override
    public void deleteById(Integer uid) {
        userRepository.deleteById(uid);
    }

    @Override
    @Transactional
    public void deletes(List<User> users) {
        for (User u : users) {
            delete(u);
        }
    }

}
