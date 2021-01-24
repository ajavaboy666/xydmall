package com.edu118.common.service.order;

import com.baomidou.mybatisplus.extension.service.IService;

import com.edu118.common.utils.PageUtils;
import com.edu118.common.entity.order.OrderItemEntity;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-21 20:01:34
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

