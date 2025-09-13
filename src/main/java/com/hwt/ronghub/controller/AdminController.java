package com.hwt.ronghub.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwt.ronghub.dto.LoginDto;
import com.hwt.ronghub.dto.PageDtoUtil;
import com.hwt.ronghub.dto.StatsDataDto;
import com.hwt.ronghub.entity.Admin;
import com.hwt.ronghub.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员
 *
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录
     *
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public Admin login(@RequestBody LoginDto loginDto) {
        return adminService.login(loginDto);
    }

    /**
     * 查找管理员
     * @param admin
     * @return
     */
    @PostMapping("/selectAdmin")
    public List<Admin> selectAdmin(@RequestBody Admin admin) {
        return adminService.selectAdmin(admin);
    }

    /**
     * 获取所有的管理员
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/getAllAdmin/{current}/{size}")
    public PageDtoUtil getAllAdmin(@PathVariable Integer current, @PathVariable Integer size) {
        return adminService.getAdminList(current, size);
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @PostMapping("/addAdmin")
    public PageDtoUtil addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    /**
     * 重置管理员密码
     * @param adminId
     */
    @GetMapping("/resetPassword/{adminId}")
    public void resetPassword(@PathVariable Integer adminId) {
        adminService.resetPassword(adminId);
    }


    /**
     * 获取可视化数据
     *
     * @return StatsDataDto
     */
    @GetMapping("/getStatsData")
    public StatsDataDto getStatsData() {
        return adminService.getStatsData();
    }

    /**
     * 修改我的密码
     * @param adminId
     * @param password
     * @param newPassword
     * @return
     */
    @GetMapping("/resetMyPassword/{adminId}/{password}/{newPassword}")
    public Admin resetMyPassword(@PathVariable Integer adminId,
                              @PathVariable String password,
                              @PathVariable String newPassword) {
        return adminService.resetMyPassword(adminId, password, newPassword);
    }

}
