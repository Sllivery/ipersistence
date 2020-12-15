package com.lux.sqlsession;

import com.lux.utils.ParameterMapping;

import java.util.List;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/15
 **/
public class BoundSql {
    private String sql;
    private List<ParameterMapping> params;

    public BoundSql(String sql, List<ParameterMapping> params) {
        this.sql = sql;
        this.params = params;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParams() {
        return params;
    }

    public void setParams(List<ParameterMapping> params) {
        this.params = params;
    }
}
