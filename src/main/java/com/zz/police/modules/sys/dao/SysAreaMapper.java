package com.zz.police.modules.sys.dao;

import com.zz.police.common.entity.Query;
import com.zz.police.modules.sys.entity.SysAreaEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 行政区域
 * @author dengkp
 */
@Mapper
public interface SysAreaMapper extends BaseMapper<SysAreaEntity> {

	/**
	 * 根据父计编码查询
	 * @param query
	 * @return
	 */
	List<SysAreaEntity> listAreaByParentCode(Query query);

	/**
	 * 子节点总数
	 * @param areaId
	 * @return
	 */
	int countAreaChildren(Long areaId);
	
}
