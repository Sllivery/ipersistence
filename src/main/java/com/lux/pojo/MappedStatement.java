package com.lux.pojo;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/11
 **/
public class MappedStatement {
    private String statementId;
    private String resultType;
    private String paraType;
    private String sql;

    public MappedStatement(String statementId, String resultType, String paraType, String sql) {
        this.statementId = statementId;
        this.resultType = resultType;
        this.paraType = paraType;
        this.sql = sql;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParaType() {
        return paraType;
    }

    public void setParaType(String paraType) {
        this.paraType = paraType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
