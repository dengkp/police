package com.zz.police.modules.sys.dao;

import com.zz.police.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统角色与菜单关系
 * @author dengkp
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

	/**
	 * 根据菜单id批量删除
	 * @param id
	 * @return
	 */
	int batchRemoveByMenuId(Long[] id);

	/**
	 * 根据角色id批量删除
	 * @param id
	 * @return
	 */
	int batchRemoveByRoleId(Long[] id);

	/**
	 * 查询角色所有菜单id集合
	 * @param id
	 * @return
	 */
	List<Long> listMenuId(Long id);
	
}
