package com.zz.police.modules.sys.service;

import com.zz.police.common.entity.Result;
import com.zz.police.modules.sys.entity.SysOrgEntity;

import java.util.List;

/**
 * 组织机构
 * @author dengkp
 */
public interface SysOrgService {

	/**
	 * 查询机构列表
	 * @return
	 */
	List<SysOrgEntity> listOrg();

	/**
	 * 查询机构列表：ztree数据源
	 * @return
	 */
	List<SysOrgEntity> listOrgTree();

	/**
	 * 新增机构
	 * @param org
	 * @return
	 */
	Result saveOrg(SysOrgEntity org);

	/**
	 * 根据id查询机构
	 * @param orgId
	 * @return
	 */
	Result getOrg(Long orgId);

	/**
	 * 更新机构
	 * @param org
	 * @return
	 */
	Result updateOrg(SysOrgEntity org);

	/**
	 * 删除机构
	 * @param id
	 * @return
	 */
	Result bactchRemoveOrg(Long[] id);

	/**
	 * 查询所有直接子机构集合
	 * @param parentId
	 * @return
	 */
	List<Long> listOrgChildren(Long parentId);

	/**
	 * 递归查询所有子机构集合
	 * @param parentId
	 * @return
	 */
	List<Long> getAllOrgChildren(Long parentId);
	
}
