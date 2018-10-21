package com.zz.police.modules.sys.service;

import com.zz.police.common.entity.Page;
import com.zz.police.common.entity.R;
import com.zz.police.modules.sys.entity.SysLogEntity;

import java.util.Map;

/**
 * 系统日志
 * @author dengkp
 */
public interface SysLogService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    Page<SysLogEntity> listLog(Map<String, Object> params);

    /**
     * 批量删除
     * @param id
     * @return
     */
    R batchRemove(Long[] id);

    /**
     * 清空日志
     * @return
     */
    R batchRemoveAll();

}
