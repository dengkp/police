package com.zz.police.common.support.orm.dialect;

/**
 * Postgre 数据库 方言
 *
 * @author dengkp
 *
 *
 * @date 2018-10-21 11:07:36
 */
public class PostgreDialect extends Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return PostgrePageHepler.getLimitString(sql, offset, limit);
    }

    @Override
    public String getCountString(String sql) {
        return PostgrePageHepler.getCountString(sql);
    }
}
