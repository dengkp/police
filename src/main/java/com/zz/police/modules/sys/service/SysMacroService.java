package com.zz.police.modules.sys.service;

import com.zz.police.common.entity.Result;
import com.zz.police.modules.sys.entity.SysMacroEntity;

import java.util.List;

/**
 * 通用字典
 * @author dengkp
 */
public interface SysMacroService {

	/**
	 * 字典列表
	 * @return
	 */
	List<SysMacroEntity> listMacro();

	/**
	 * 字典上级列表：ztree数据源
	 * @return
	 */
	List<SysMacroEntity> listNotMacro();

	/**
	 * 新增字典
	 * @param macro
	 * @return
	 */
	Result saveMacro(SysMacroEntity macro);

	/**
	 * 根据id查询字典
	 * @param id
	 * @return
	 */
	Result getObjectById(Long id);

	/**
	 * 修改字典
	 * @param macro
	 * @return
	 */
	Result updateMacro(SysMacroEntity macro);

	/**
	 * 批量删除字典
	 * @param id
	 * @return
	 */
	Result batchRemove(Long[] id);

	/**
	 * 查询指定类型的参数列表
	 * @param type
	 * @return
	 */
	List<SysMacroEntity> listMacroValue(String type);
	
}
