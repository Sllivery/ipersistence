package com.lux.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/11
 **/
public class Configuration {
    private DataSource dataSource;
    private Map<String, MappedStatement> statementMap = new HashMap<>();

    public Configuration() {
    }

    public Configuration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getStatementMap() {
        return statementMap;
    }

    public void setStatementMap(Map<String, MappedStatement> statementMap) {
        this.statementMap = statementMap;
    }
}
