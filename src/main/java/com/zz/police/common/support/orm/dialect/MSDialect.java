package com.zz.police.common.support.orm.dialect;

/**
 * MSSQL 数据库方言
 *
 * @author dengkp
 *
 *
 * @date 2018-10-21 110650
 */
public class MSDialect extends Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return MSPageHepler.getLimitString(sql, offset, limit);
    }

    @Override
    public String getCountString(String sql) {
        return MSPageHepler.getCountString(sql);
    }
}
