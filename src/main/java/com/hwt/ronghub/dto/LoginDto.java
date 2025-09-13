package com.hwt.ronghub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录传输对象
 *
 * @Auther hwtzzZ
 * 创建于  2024-10-10
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    private String username;

    private String password;

    //管理员类型
    private Integer adminType;

}
