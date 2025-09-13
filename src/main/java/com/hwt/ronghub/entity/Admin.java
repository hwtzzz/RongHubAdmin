package com.hwt.ronghub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统管理员
 *
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    // 管理员编号
    @TableId(value = "adminId", type = IdType.AUTO)
    private Integer adminId;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 管理员昵称
    private String nickname;

    //管理员电话
    private String phone;

    //管理员的类型 1 景区工作人员 2 系统管理员
    private Integer adminType;

    private Integer scenicId;

}
