package com.zz.police.modules.sys.service.impl;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zz.police.common.entity.Page;
import com.zz.police.common.entity.Query;
import com.zz.police.modules.sys.dao.SysGeneratorMapper;
import com.zz.police.modules.sys.entity.ColumnEntity;
import com.zz.police.modules.sys.entity.GeneratorParamEntity;
import com.zz.police.modules.sys.entity.TableEntity;
import com.zz.police.modules.sys.generator.GenUtils;
import com.zz.police.modules.sys.service.SysGeneratorService;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * @author dengkp
 */
@Service("sysGeneratorService")
public class SysGeneratorServiceImpl implements SysGeneratorService {

	@Autowired
	private SysGeneratorMapper sysGeneratorMapper;

	/**
	 * 查询所有表格
	 * @param params
	 * @return
	 */
	@Override
	public Page<TableEntity> listTable(Map<String, Object> params) {
		Query query = new Query(params);
		Page<TableEntity> page = new Page<>(query);
		sysGeneratorMapper.listTable(page, query);
		return page;
	}

	/**
	 * 生成代码
	 * @param params
	 * @return
	 */
	@Override
	public byte[] generator(GeneratorParamEntity params) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(out);
		for(String table : params.getTables()) {
			TableEntity tableEntity = sysGeneratorMapper.getTableByName(table);
			List<ColumnEntity> columns = sysGeneratorMapper.listColumn(table);
			GenUtils.generatorCode(tableEntity, columns, params, zip);
		}
		IOUtils.closeQuietly(zip);
		return out.toByteArray();
	}

}
