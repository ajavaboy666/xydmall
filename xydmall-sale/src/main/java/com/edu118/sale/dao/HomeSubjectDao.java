package com.edu118.sale.dao;

import com.edu118.common.entity.sale.HomeSubjectEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:49:39
 */
@Mapper
public interface HomeSubjectDao extends BaseMapper<HomeSubjectEntity> {

}
