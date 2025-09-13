package com.hwt.ronghub.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hwt.ronghub.dto.ActivityCommentList;
import com.hwt.ronghub.dto.ActivityListDto;
import com.hwt.ronghub.entity.Activity;
import com.hwt.ronghub.entity.ActivityComment;
import com.hwt.ronghub.entity.ActivityLike;
import com.hwt.ronghub.mapper.ActivityCommentMapper;
import com.hwt.ronghub.mapper.ActivityLikeMapper;
import com.hwt.ronghub.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther hwtzzZ
 * 创建于  2024-11-24
 * @Version: 1.0
 */
@Service
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityLikeMapper activityLikeMapper;
    @Autowired
    private ActivityCommentMapper activityCommentMapper;


    //获取热门
    public List<ActivityListDto> getHotActivity(Integer userId) {
        List<ActivityListDto> hotActivity = activityMapper.getHotActivity();
        return getActivityLikeList(hotActivity, userId);
    }

    //获取最新
    public List<ActivityListDto> getNewActivity(Integer userId) {
        List<ActivityListDto> newActivity = activityMapper.getNewActivity();
        return getActivityLikeList(newActivity, userId);
    }

    //判断用户是否点赞
    private List<ActivityListDto> getActivityLikeList(List<ActivityListDto> newActivity, Integer userId) {
        for (ActivityListDto a : newActivity) {
            int like = activityLikeMapper.selectCount(new LambdaQueryWrapper<ActivityLike>()
                    .eq(ActivityLike::getActivityId, a.getActivityId())
                    .eq(ActivityLike::getUserId, userId));
            // 1是点赞 2是未点赞
            a.setLike(like == 0 ? 1 : 2);
        }
        return newActivity;
    }

    public List<ActivityCommentList> getActivityComment() {
        return activityLikeMapper.getActivityComment();
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ActivityListDto> likeActivityOrCancel(Integer userId, Integer activityId, Integer type) {
        ActivityLike activityLike = activityLikeMapper.selectOne(new LambdaQueryWrapper<ActivityLike>()
                .eq(ActivityLike::getUserId, userId)
                .eq(ActivityLike::getActivityId, activityId));
        // 查询活动信息
        Activity activity = activityMapper.selectById(activityId);
        // 判断 activityLike 是否为 null
        if (activityLike == null) {
            // 添加一条点赞信息
            ActivityLike a = new ActivityLike();
            a.setActivityId(activityId);
            a.setUserId(userId);
            activityLikeMapper.insert(a);
            // 点赞量 +1
            activity.setSupport(activity.getSupport() + 1);
        } else {
            // 点赞量 -1
            activity.setSupport(activity.getSupport() - 1);
            // 删除点赞信息
            activityLikeMapper.deleteById(activityLike.getLikeId());
        }
        // 更新活动信息
        activityMapper.updateById(activity);
        return type == 1 ? getHotActivity(userId) : getNewActivity(userId);
    }

    public List<ActivityCommentList> addActivityComment(ActivityComment activityComment) {
        activityComment.setSendTime(new Date());
        activityCommentMapper.insert(activityComment);
        return getActivityComment();
    }

    public List<Activity> getMyLikeActivity(Integer userId) {
        // 查询用户点赞的活动ID列表
        List<ActivityLike> activityLikes = activityLikeMapper.selectList(
                new LambdaQueryWrapper<ActivityLike>()
                        .eq(ActivityLike::getUserId, userId)
        );
        // 提取活动ID列表
        List<Integer> activityIds = activityLikes.stream()
                .map(ActivityLike::getActivityId)
                .collect(Collectors.toList());
        // 如果活动ID列表为空，直接返回空列表
        if (activityIds.isEmpty()) {
            return Collections.emptyList();
        }
        // 批量查询活动信息
        return activityMapper.selectBatchIds(activityIds);
    }
}
