package com.zz.police.modules.sys.dao;

import com.zz.police.modules.sys.entity.QuartzJobEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * 定时任务
 * @author dengkp
 */
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJobEntity> {

}
