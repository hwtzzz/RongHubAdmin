package com.hwt.ronghub.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 存放列表数据
     *
     * @param key   Redis 键
     * @param list  要存储的列表数据
     */
    public void setList(String key, List<Object> list) {
        // 清空原有数据
        redisTemplate.delete(key);
        // 存储新数据
        redisTemplate.opsForList().rightPushAll(key, list);
    }

    /**
     * 获取列表数据
     *
     * @param key Redis 键
     * @return 列表数据
     */
    public List<Object> getList(String key) {
        // 获取整个列表
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 向列表添加数据
     *
     * @param key   Redis 键
     * @param value 要添加的数据
     */
    public void addToList(String key, Object value) {
        // 将数据添加到列表末尾
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 清空列表
     *
     * @param key Redis 键
     */
    public void clearList(String key) {
        // 删除键
        redisTemplate.delete(key);
    }

}