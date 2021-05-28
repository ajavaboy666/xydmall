package com.edu118.order.dao;

import com.edu118.common.entity.order.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-21 20:01:34
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

}
