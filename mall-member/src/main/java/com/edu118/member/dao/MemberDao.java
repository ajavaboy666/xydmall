package com.edu118.member.dao;

import com.edu118.common.entity.member.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 *
 * @author huangsw
 * @email 512300199@qq.com
 * @date 2021-01-22 20:50:59
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {

}
