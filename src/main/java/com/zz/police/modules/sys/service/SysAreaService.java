package com.zz.police.modules.sys.service;

import com.zz.police.common.entity.Result;
import com.zz.police.modules.sys.entity.SysAreaEntity;

import java.util.List;
import java.util.Map;

/**
 * 行政区域
 * @author dengkp
 */
public interface SysAreaService {

	/**
	 * 根据父级id查询区域：ztree异步数据源
	 * @param areaCode
	 * @return
	 */
	List<SysAreaEntity> listAreaByParentCode(String areaCode);

	/**
	 * 根据父级id查询区域：表格数据源
	 * @param params
	 * @return
	 */
	Result listAreaByParentCode(Map<String, Object> params);

	/**
	 * 新增区域
	 * @param area
	 * @return
	 */
	Result saveArea(SysAreaEntity area);

	/**
	 * 根据id查询区域
	 * @param areaId
	 * @return
	 */
	Result getAreaById(Long areaId);

	/**
	 * 修改区域
	 * @param area
	 * @return
	 */
	Result updateArea(SysAreaEntity area);

	/**
	 * 批量删除区域
	 * @param id
	 * @return
	 */
	Result batchRemoveArea(Long[] id);
	
}
