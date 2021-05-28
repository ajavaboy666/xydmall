package com.edu118.common.service.member;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.entity.member.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:26:54
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

