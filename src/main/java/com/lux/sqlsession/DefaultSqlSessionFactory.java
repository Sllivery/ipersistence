package com.lux.sqlsession;

import com.lux.pojo.Configuration;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/11
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return null;
    }
}
