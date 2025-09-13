package com.hwt.ronghub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息
 *
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // 用户编号
    @TableId(value = "userId", type = IdType.AUTO)
    private Integer userId;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 头像
    private String avatar;

    // 用户昵称
    private String nickname;

    // 用户电话
    private String tel;

    //所在区域
    private String address;

    //性别
    private String sex;

    //是否被拉黑 0未拉黑 1已拉黑
    private Integer black;

}
