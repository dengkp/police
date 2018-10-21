package com.zz.police.modules.sys.service;

import com.zz.police.common.entity.Page;
import com.zz.police.common.entity.Result;
import com.zz.police.modules.sys.entity.QuartzJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 * @author dengkp
 */
public interface QuartzJobLogService {

	/**
	 * 分页查询任务日志
	 * @param params
	 * @return
	 */
	Page<QuartzJobLogEntity> listForPage(Map<String, Object> params);

	/**
	 * 批量删除日志
	 * @param id
	 * @return
	 */
	Result batchRemove(Long[] id);

	/**
	 * 清空日志
	 * @return
	 */
	Result batchRemoveAll();
	
}
