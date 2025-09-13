package com.hwt.ronghub.controller;

import com.hwt.ronghub.entity.SysConfig;
import com.hwt.ronghub.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/sysConfig")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 获取系统的全局信息
     * @return
     */
    @GetMapping("/getSysConfig")
    public SysConfig getSysConfig(){
        return sysConfigService.getSysConfig();
    }

}
