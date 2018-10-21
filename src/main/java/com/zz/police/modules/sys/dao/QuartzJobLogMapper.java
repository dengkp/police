package com.zz.police.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zz.police.modules.sys.entity.QuartzJobLogEntity;

/**
 * 定时任务日志
 * @author dengkp
 */
@Mapper
public interface QuartzJobLogMapper extends BaseMapper<QuartzJobLogEntity> {

	/**
	 * 批量删除
	 * @return
	 */
	int batchRemoveAll();
	
}
