package com.hwt.ronghub.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwt.ronghub.dto.LoginDto;
import com.hwt.ronghub.dto.PageDtoUtil;
import com.hwt.ronghub.entity.User;
import com.hwt.ronghub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public User login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    /**
     * 查询用户
     * @param user
     * @return
     */
    @PostMapping("/queryUser")
    public List<User> queryUser(@RequestBody User user) {
        return userService.queryUser(user);
    }

    /**
     * 获取所有用户
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/getAllUser/{current}/{size}")
    public PageDtoUtil getAllUser(@PathVariable Integer current, @PathVariable Integer size) {
        return userService.getAllUser(current, size);
    }

    /**
     * 拉黑用户
     * @param userId
     * @return
     */
    @GetMapping("/blackUser/{userId}")
    public Page<User> blackUser(@PathVariable Integer userId) {
        return userService.blackUser(userId);
    }

    /**
     * 重置用户密码
     * @param userId
     */
    @GetMapping("/resetPassword/{userId}")
    public void resetPassword(@PathVariable Integer userId) {
        userService.resetPassword(userId);
    }

    /**
     * 用户更新密码
     * @param userId
     * @param password
     * @param newPassword
     * @return
     * @throws Exception
     */
    @GetMapping("/updatePassword/{userId}/{password}/{newPassword}")
    public User updatePassword(@PathVariable Integer userId,
                               @PathVariable String password,
                               @PathVariable String newPassword) throws Exception {
        return userService.updatePassword(userId, password, newPassword);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateUserInfo")
    public User updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    /**
     * 注册用户
     * @param user
     */
    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userService.register(user);
    }

}
