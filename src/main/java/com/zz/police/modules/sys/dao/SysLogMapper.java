package com.zz.police.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zz.police.modules.sys.entity.SysLogEntity;

/**
 * 系统日志
 * @author dengkp
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

	/**
	 * 批量删除
	 * @return
	 */
	int batchRemoveAll();
	
}
