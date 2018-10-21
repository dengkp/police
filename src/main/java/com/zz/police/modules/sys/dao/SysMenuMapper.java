package com.zz.police.modules.sys.dao;

import com.zz.police.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统菜单dao
 * @author dengkp
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {

	/**
	 * 根据父级id查询菜单
	 * @param parentId
	 * @return
	 */
	List<SysMenuEntity> listParentId(Long parentId);

	/**
	 * 查询菜单目录和菜单集合
	 * @return
	 */
	List<SysMenuEntity> listNotButton();

	/**
	 * 用户权限菜单
	 * @param userId
	 * @return
	 */
	List<String> listUserPerms(Long userId);

	/**
	 * 菜单子节点个数
	 * @param parentId
	 * @return
	 */
	int countMenuChildren(Long parentId);

}
