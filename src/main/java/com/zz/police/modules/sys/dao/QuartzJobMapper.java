package com.zz.police.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zz.police.modules.sys.entity.QuartzJobEntity;


/**
 * 定时任务
 * @author dengkp
 */
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJobEntity> {

}
