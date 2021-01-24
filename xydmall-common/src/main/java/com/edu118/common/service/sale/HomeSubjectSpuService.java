package com.edu118.common.service.sale;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu118.common.utils.PageUtils;
import com.edu118.common.entity.sale.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * 专题商品
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:24:50
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

