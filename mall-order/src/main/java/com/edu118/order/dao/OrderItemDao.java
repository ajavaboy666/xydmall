package com.edu118.order.dao;

import com.edu118.common.entity.order.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-21 20:01:34
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {

}
