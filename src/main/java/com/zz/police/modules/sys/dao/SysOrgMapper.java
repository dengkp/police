package com.zz.police.modules.sys.dao;

import com.zz.police.modules.sys.entity.SysOrgEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 组织架构
 *
 * @author dengkp
 *
 *
 * @date 2017年8月17日 11:26:05
 */
@Mapper
public interface SysOrgMapper extends BaseMapper<SysOrgEntity> {

	/**
	 * 统计子机构数量
	 * @param parentId
	 * @return
	 */
	int countOrgChildren(Long parentId);

	/**
	 * 查询子机构集合
	 * @param parentId
	 * @return
	 */
	List<Long> listOrgChildren(Long parentId);
	
}
