/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mysite.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mycompany.mysite.domain.User;
import com.mycompany.mysite.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Table;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Spring MVC Controller: 1、查询所有的符合条件的用户信息，并分页传送给界面 2、编辑方法从数据库查询需要的数据
 * 3、存储方法把新增的用户或编辑后用户信息存储到数据库 4、删除用户 5、锁定用户，有效状态改变
 *
 * @author WIN11
 */
@Controller
@Table(name = "USERS")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    /**
     * 根据条件查询用户信息
     *
     * @param kw 查询关键字即条件
     * @param model 模型对性，也是视图的上下文环境对象
     * @param pageable 分页信息对象，包含了分页需要的基本信息，如当前页码、每页需要的条数
     * @return 字符串，代表了页面文件
     */
    @RequestMapping("/listusers")
    public String list(String kw, Model model, Pageable pageable) {
        model.addAttribute("kw",kw);
        if(kw !=null) kw = "%"+kw+"%";
        else kw = "%";
        Page<User> ppu = userService.findAll(kw, pageable);//默认分页从0页面（第一页），取每页20条数据
        model.addAttribute("pages", ppu);
        return "listusers";//返回界面
    }

    @GetMapping({"/edituser", "/edituser/{uid}"})
    public String edit(@PathVariable(name = "uid", required = false) Integer uid, Model model) {
        User u = new User();
        if (uid != null && uid > 0) {
            u = userService.findById(uid);
        }
        model.addAttribute("user", u);
        model.addAttribute("sexs",User.Sex.toList());
//        ModelAndView mv = new ModelAndView("edituser");
//        mv.addObject("user", u);
        return "edituser";
    }

    @PostMapping("/saveuser")
    public String save(@Valid User user, BindingResult result, RedirectAttributes attr) {
        try {
            if (result.hasErrors()) {
                return "redirect:/edituser";
            }
            if (user.getUid()!=null&&user.getUid()>0){
                User u = userService.findById(user.getUid());
                user.setPassword(u.getPassword());
            }
            user.setUid(1);
            userService.save(user);
            logger.info("user has registered {}...",user.getName());
            attr.addFlashAttribute("ok","保存成功");
            return "redirect:/listusers";
        } catch  (Exception ex){
            logger.info("user has registered failed {}...",user.getName());
            return "redirect:/edituser";
        }
    }

    @GetMapping("/deleteuser/{uid}")
    public String delete(@PathVariable("uid") Integer uid) {
        userService.deleteById(uid);
        return "redirect:/listusers";
    }

    @PostMapping("/deleteusers")
    public String delete(String uids) {
        List<User> users = new ArrayList<>();
        JSONObject json = JSONObject.parseObject(uids);
        JSONArray arr = json.getJSONArray("uids");//前端传递时使用uids作为json数据的键
        int ilen = arr.size();
        for (int i = 0; i < ilen; i++) {
            users.add(userService.findById(arr.getInteger(i)));
        }
        userService.deletes(users);
        return "redirect:/listusers";
    }

    @GetMapping("/validuser/{uid}")
    public String validuser(@PathVariable("uid") Integer uid) {
        User u = userService.findById(uid);
        u.setValidstate(1 - u.getValidstate());
        return "redirect:/listusers";
    }
}
