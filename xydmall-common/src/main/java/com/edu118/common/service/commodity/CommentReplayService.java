package com.edu118.common.service.commodity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.entity.commodity.CommentReplayEntity;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:21:22
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

