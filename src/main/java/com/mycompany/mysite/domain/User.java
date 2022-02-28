/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mysite.domain;

import com.sun.istack.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author WIN11
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer uid;
    @Column(length=30,unique=true)
    @NotNull
    private String account;
    @Column(length=30)
    @NotNull
    private String password;
    @Column(length=30)
    @NotNull
    private String name;
    public enum Sex{
        男,女;
        public static List<String> toList(){
            Sex[] sex = Sex.values();
            List<String> datas = new ArrayList<>();
            for(Sex x:sex){
                datas.add(x.name());
            }
            return datas;
        }
    };
    private Sex grander;//实际输入数据库的是索引值， 男为0女为1
    private LocalDate brithday;
    @Column(length=11)
    private String mobile;
    @Column(length=100)
    private String email;
    private Integer lasttime;//最后登陆时间
    private Integer logincount;//登录次数
    private Integer validstate=1;//用户是否有效状态

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getGrander() {
        return grander;
    }

    public void setGrander(Sex grander) {
        this.grander = grander;
    }

    public LocalDate getBrithday() {
        return brithday;
    }

    public void setBrithday(LocalDate brithday) {
        this.brithday = brithday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLasttime() {
        return lasttime;
    }

    public void setLasttime(Integer lasttime) {
        this.lasttime = lasttime;
    }

    public Integer getLogincount() {
        return logincount;
    }

    public void setLogincount(Integer logincount) {
        this.logincount = logincount;
    }

    public Integer getValidstate() {
        return validstate;
    }

    public void setValidstate(Integer validstate) {
        this.validstate = validstate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.uid);
        hash = 83 * hash + Objects.hashCode(this.account);
        hash = 83 * hash + Objects.hashCode(this.password);
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.grander);
        hash = 83 * hash + Objects.hashCode(this.brithday);
        hash = 83 * hash + Objects.hashCode(this.mobile);
        hash = 83 * hash + Objects.hashCode(this.email);
        hash = 83 * hash + Objects.hashCode(this.lasttime);
        hash = 83 * hash + Objects.hashCode(this.logincount);
        hash = 83 * hash + Objects.hashCode(this.validstate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.account, other.account)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.mobile, other.mobile)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.uid, other.uid)) {
            return false;
        }
        if (this.grander != other.grander) {
            return false;
        }
        if (!Objects.equals(this.brithday, other.brithday)) {
            return false;
        }
        if (!Objects.equals(this.lasttime, other.lasttime)) {
            return false;
        }
        if (!Objects.equals(this.logincount, other.logincount)) {
            return false;
        }
        return Objects.equals(this.validstate, other.validstate);
    }

    @Override
    public String toString() {
        return "User{" + "uid=" + uid + ", account=" + account + ", password=" + password + ", name=" + name + ", grander=" + grander + ", brithday=" + brithday + ", mobile=" + mobile + ", email=" + email + ", lasttime=" + lasttime + ", logincount=" + logincount + ", validstate=" + validstate + '}';
    }
    
}
