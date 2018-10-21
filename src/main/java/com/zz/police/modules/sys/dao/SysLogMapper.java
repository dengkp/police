package com.zz.police.modules.sys.dao;

import com.zz.police.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

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
