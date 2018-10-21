package com.zz.police.modules.sys.service;

import java.util.Map;

import com.zz.police.common.entity.Page;
import com.zz.police.modules.sys.entity.GeneratorParamEntity;
import com.zz.police.modules.sys.entity.TableEntity;

/**
 * 代码生成器
 * @author dengkp
 */
public interface SysGeneratorService {

	/**
	 * 分页查询表格
	 * @param params
	 * @return
	 */
	Page<TableEntity> listTable(Map<String, Object> params);

	/**
	 * 生成代码
	 * @param params
	 * @return
	 */
	byte[] generator(GeneratorParamEntity params);
	
}
