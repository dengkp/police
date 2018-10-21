package com.zz.police.modules.sys.dao;

import com.zz.police.common.entity.Page;
import com.zz.police.common.entity.Query;
import com.zz.police.modules.sys.entity.ColumnEntity;
import com.zz.police.modules.sys.entity.TableEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 代码生成器
 * @author dengkp
 */
@Mapper
public interface SysGeneratorMapper {

	/**
	 * 查询所有表格
	 * @param page
	 * @param query
	 * @return
	 */
	List<TableEntity> listTable(Page<TableEntity> page, Query query);

	/**
	 * 根据名称查询
	 * @param tableName
	 * @return
	 */
	TableEntity getTableByName(String tableName);

	/**
	 * 查询所有列
	 * @param tableName
	 * @return
	 */
	List<ColumnEntity> listColumn(String tableName);
	
}
