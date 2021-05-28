package com.edu118.commodity.dao;

import com.edu118.common.entity.commodity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:47:49
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {

}
