package com.hwt.ronghub.service;

import com.hwt.ronghub.entity.SysConfig;
import com.hwt.ronghub.mapper.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Service
public class SysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    public SysConfig getSysConfig() {
        return sysConfigMapper.selectById(1);
    }
}
