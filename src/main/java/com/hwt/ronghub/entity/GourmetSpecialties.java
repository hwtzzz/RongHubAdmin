package com.hwt.ronghub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hwt.ronghub.config.AppConsts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 美食特产
 * @Auther hwtzzZ
 * 创建于  2024-11-11
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GourmetSpecialties {

    // 美食特产编号
    @TableId(value = "gourmetSpecialtiesId", type = IdType.AUTO)
    private Integer gourmetSpecialtiesId;

    //美食或特产名称
    private String name;

    //发布时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConsts.dateFormatPattern, timezone = "GMT+8")
    @DateTimeFormat(pattern = AppConsts.dateFormatPattern)
    private Date publicTime;

    //内容
    private String content;

    //美食照片
    private String image;

    //特产还是美食 0:特产 1:美食
    private int gsType;



}
