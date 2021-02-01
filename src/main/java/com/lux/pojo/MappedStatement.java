package com.lux.pojo;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/11
 **/
public class MappedStatement {
    private String statementId;
    private String resultClass;
    private String paraClass;
    private String sql;

    public MappedStatement(String statementId, String resultClass, String paraClass, String sql) {
        this.statementId = statementId;
        this.resultClass = resultClass;
        this.paraClass = paraClass;
        this.sql = sql;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getResultClass() {
        return resultClass;
    }

    public void setResultClass(String resultClass) {
        this.resultClass = resultClass;
    }

    public String getParaClass() {
        return paraClass;
    }

    public void setParaClass(String paraClass) {
        this.paraClass = paraClass;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
