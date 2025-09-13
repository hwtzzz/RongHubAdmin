package com.hwt.ronghub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwt.ronghub.dto.AddressCountDto;
import com.hwt.ronghub.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther hwtzzZ
 * 创建于  2024-10-09
 * @Version: 1.0
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("" +
            "SELECT\n" +
            "\taddress AS name,\n" +
            "\tCOUNT( userId ) AS `value` \n" +
            "FROM\n" +
            "USER \n" +
            "GROUP BY\n" +
            "\taddress \n" +
            "ORDER BY\n" +
            "\t`value` DESC;" +
            "")
    List<AddressCountDto> getAddressCount();
}
