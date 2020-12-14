package com.lux.pojo;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/11
 **/
public class Configuration {
    private DataSource dataSource;
    private Map<String, MappedStatement> statementMap;

    public Configuration() {
    }

    public Configuration(DataSource dataSource, Map<String, MappedStatement> statementMap) {
        this.dataSource = dataSource;
        this.statementMap = statementMap;
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
