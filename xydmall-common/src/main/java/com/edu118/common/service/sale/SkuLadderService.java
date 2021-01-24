package com.edu118.common.service.sale;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.entity.sale.SkuLadderEntity;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:24:50
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

