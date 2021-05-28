package com.edu118.common.service.warehouse;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.entity.warehouse.PurchaseDetailEntity;

import java.util.Map;

/**
 * 采购明细
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:29:01
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

