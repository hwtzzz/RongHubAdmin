package com.hwt.ronghub.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwt.ronghub.dto.AddressCountDto;
import com.hwt.ronghub.dto.LoginDto;
import com.hwt.ronghub.dto.PageDtoUtil;
import com.hwt.ronghub.dto.StatsDataDto;
import com.hwt.ronghub.entity.Admin;
import com.hwt.ronghub.entity.SysConfig;
import com.hwt.ronghub.entity.User;
import com.hwt.ronghub.mapper.AdminMapper;
import com.hwt.ronghub.mapper.ScenicMapper;
import com.hwt.ronghub.mapper.SysConfigMapper;
import com.hwt.ronghub.mapper.UserMapper;
import com.hwt.ronghub.utils.AESUtil;
import com.hwt.ronghub.utils.RedisUtil;
import com.hwt.ronghub.utils.UserFriendlyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private SysConfigMapper sysConfigMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ScenicMapper scenicMapper;
    @Autowired
    RedisUtil redisUtil;

    public Admin login(LoginDto loginDto) {
        //加密传进来的字符串
        String encrypt = null;
        try {
            encrypt = AESUtil.encrypt(loginDto.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Admin admin = adminMapper.selectOne(new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUsername, loginDto.getUsername())
                .eq(Admin::getPassword, encrypt)
                .eq(Admin::getAdminType, loginDto.getAdminType()));
        UserFriendlyException.throwException(admin == null, "用户名或密码错误！");
        //更新平台访问量
        SysConfig sysConfig = sysConfigMapper.selectById(1);
        sysConfig.setVisitedNumber(sysConfig.getVisitedNumber() + 1);
        sysConfigMapper.updateById(sysConfig);
        return admin;
    }

    public StatsDataDto getStatsData() {
        StatsDataDto statsDataDto = new StatsDataDto();
        //系统总人数
        Integer userNumber = userMapper.selectCount(null);
        Integer adminNumber = adminMapper.selectCount(null);
        Integer count = userNumber + adminNumber;
        statsDataDto.setSystemUserNumber(count);
        //平台访问量
        SysConfig sysConfig = sysConfigMapper.selectById(1);
        statsDataDto.setSystemVisited(sysConfig.getVisitedNumber());
        //景区数量
        Integer scenicNumber = scenicMapper.selectCount(null);
        statsDataDto.setScenicNumber(scenicNumber);
        //平台开放天数
        Date openDay = sysConfig.getOpenDate();
        Date now = new Date();
        long starTime = openDay.getTime();
        long endTime = now.getTime();
        statsDataDto.setOpenDayNumber((endTime - starTime) / 24 / 60 / 60 / 1000);
        //设置每个地区的人数
        List<AddressCountDto> list = adminMapper.getAddressCount();
        statsDataDto.setList(list);
        return statsDataDto;
    }

    public PageDtoUtil getAdminList(Integer currentPage, Integer pageSize) {
        List<Object> admins = redisUtil.getList("admin");
        return PageDtoUtil.getPageDto(admins, currentPage, pageSize);
    }

    public List<Admin> selectAdmin(Admin admin) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(admin.getUsername())) {
            wrapper.like(Admin::getUsername, admin.getUsername());
        }
        if (StringUtils.isNotBlank(admin.getPhone())) {
            wrapper.eq(Admin::getPhone, admin.getPhone());
        }
        if (admin.getAdminType() != null) {
            wrapper.eq(Admin::getAdminType, admin.getAdminType());
        }
        return adminMapper.selectList(wrapper);
    }

    public PageDtoUtil addAdmin(Admin admin) {
        Admin a = adminMapper.selectOne(new LambdaQueryWrapper<Admin>().eq(Admin::getUsername, admin.getUsername()));
        UserFriendlyException.throwException(a != null, "用户名已被使用!");
        admin.setPassword("w1Q3K465Poc7B+z58U/VSA==");
        if (admin.getAdminType() == 1) {
            admin.setScenicId(0);
        }
        adminMapper.insert(admin);
        updateAdminList();
        return getAdminList(1 , 8);
    }

    public void updateAdminList() {
        //更新到redis  强制查询最新数据
        List<Admin> adminList = adminMapper.selectList(new QueryWrapper<>());
        List<Object> objectList = new ArrayList<>(adminList); // 转换为 List<Object>
        redisUtil.setList("admin", objectList);
    }

    public void resetPassword(Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        try {
            admin.setPassword(AESUtil.encrypt("123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        adminMapper.updateById(admin);
        updateAdminList();
    }

    public Admin resetMyPassword(Integer adminId, String password, String newPassword) {
        Admin admin = adminMapper.selectById(adminId);
        String p = null;
        try {
            p = AESUtil.encrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserFriendlyException.throwException(!p.equals(admin.getPassword()), "原密码错误!");
        try {
            admin.setPassword(AESUtil.encrypt(newPassword));
        } catch (Exception e) {
            e.printStackTrace();
        }
        adminMapper.updateById(admin);
        updateAdminList();
        return admin;
    }
}
