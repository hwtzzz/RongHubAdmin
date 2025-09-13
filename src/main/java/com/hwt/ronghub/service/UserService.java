package com.hwt.ronghub.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwt.ronghub.dto.LoginDto;
import com.hwt.ronghub.dto.PageDtoUtil;
import com.hwt.ronghub.entity.SysConfig;
import com.hwt.ronghub.entity.User;
import com.hwt.ronghub.mapper.SysConfigMapper;
import com.hwt.ronghub.mapper.UserMapper;
import com.hwt.ronghub.utils.AESUtil;
import com.hwt.ronghub.utils.RedisUtil;
import com.hwt.ronghub.utils.UserFriendlyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    SysConfigMapper sysConfigMapper;
    @Autowired
    RedisUtil redisUtil;

    public User login(LoginDto loginDto) {
        //加密密码
        String encrypt = null;
        try {
            encrypt = AESUtil.encrypt(loginDto.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //查找用户信息
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, loginDto.getUsername())
                        .eq(User::getPassword, encrypt));
        UserFriendlyException.throwException(user == null, "用户名或密码错误!");
        UserFriendlyException.throwException(user.getBlack() == 1, "此用户已被拉黑!");
        //平台访问量+1
        SysConfig sysConfig = sysConfigMapper.selectById(1);
        sysConfig.setVisitedNumber(sysConfig.getVisitedNumber() + 1);
        sysConfigMapper.updateById(sysConfig);
        return user;
    }

    //获取所有用户
    public PageDtoUtil getAllUser(Integer current, Integer size) {
        List<Object> user = redisUtil.getList("user");
        return PageDtoUtil.getPageDto(user, current, size);
    }

    //拉黑用户
    public Page<User> blackUser(Integer userId) {
        User user = userMapper.selectById(userId);
        user.setBlack(user.getBlack() == 0 ? 1 : 0);
        userMapper.updateById(user);
        Page<User> userPage = new Page<>(1, 8);
        updateUserList();
        return userMapper.selectPage(userPage, null);
    }

    //查找用户信息
    public List<User> queryUser(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(user.getUsername())) {
            wrapper.like(User::getUsername, user.getUsername());
        }
        if (StringUtils.isNotBlank(user.getTel())) {
            wrapper.eq(User::getTel, user.getTel());
        }
        if (StringUtils.isNotBlank(user.getSex())) {
            wrapper.eq(User::getSex, user.getSex());
        }
        return userMapper.selectList(wrapper);
    }

    public void resetPassword(Integer userId) {
        User user = userMapper.selectById(userId);
        try {
            user.setPassword("w1Q3K465Poc7B+z58U/VSA==");
        } catch (Exception e) {
            e.printStackTrace();
        }
        userMapper.updateById(user);
        updateUserList();
    }

    @Transactional
    public User updatePassword(Integer userId, String password, String newPassword) throws Exception {
        //判断密码是否与之前的一致
        User user = userMapper.selectById(userId);
        String encrypt = AESUtil.encrypt(password);
        UserFriendlyException.throwException(!encrypt.equals(user.getPassword()), "原密码错误!");
        user.setPassword(AESUtil.encrypt(newPassword));
        updateUserList();
        return user;
    }

    public void updateUserList() {
        //更新到redis  强制查询最新数据
        List<User> userList = userMapper.selectList(new QueryWrapper<>());
        System.out.println(userList);
        List<Object> objectList = new ArrayList<>(userList); // 转换为 List<Object>
        redisUtil.setList("user", objectList);
    }

    public User updateUserInfo(User user) {
        User u = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername())
                .ne(User::getUserId, user.getUserId()));
        UserFriendlyException.throwException(u != null, "用户名已存在!");
        userMapper.updateById(user);
        //更新到redis
        updateUserList();
        return user;
    }

    public void register(User user) {
        User u = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername()));
        UserFriendlyException.throwException(u != null, "此用户名已被注册!");
        user.setPassword("w1Q3K465Poc7B+z58U/VSA==");
        user.setBlack(0);
        if (user.getSex().equals("男")) {
            user.setAvatar("http://localhost:8989/image/nan.jpg");
        } else {
            user.setAvatar("http://localhost:8989/image/nv.jpg");
        }
        userMapper.insert(user);
        //更新到redis
        updateUserList();
        userMapper.insert(user);
    }
}
