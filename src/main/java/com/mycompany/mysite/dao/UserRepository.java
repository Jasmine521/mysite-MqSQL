/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mysite.dao;

import com.mycompany.mysite.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author WIN11
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    @Query("select u from User u where account like ?1 or name like ?1 or "
            + "mobile like ?1 or email like ?1")
    public Page<User> findByKeyword(String kw,Pageable pageable);//模糊查询
    
    @Query("update User u set password=?1 where uid=?2")
    public void modifyPassword(String pwd, Integer uid);
    
}
