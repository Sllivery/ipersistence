package com.lux.pojo;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/11
 **/
public class MappedStatement {
    private String statementId;
    private Class<?> resultClass;
    private Class<?> paraClass;
    private String sql;

    public MappedStatement(String statementId, Class<?> resultClass, Class<?> paraClass, String sql) {
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

    public Class<?> getResultClass() {
        return resultClass;
    }

    public void setResultClass(Class<?> resultClass) {
        this.resultClass = resultClass;
    }

    public Class<?> getParaClass() {
        return paraClass;
    }

    public void setParaClass(Class<?> paraClass) {
        this.paraClass = paraClass;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
