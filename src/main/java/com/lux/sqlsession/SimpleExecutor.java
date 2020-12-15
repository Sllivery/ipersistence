package com.lux.sqlsession;

import com.lux.pojo.Configuration;
import com.lux.pojo.MappedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/15
 **/
public class SimpleExecutor implements Executor{
    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        //传统的jdbc,获取连接,连接获取预处理对象,预处理对象填参数,然后执行
        Connection connection = configuration.getDataSource().getConnection();
        //需要拼接SQL
            //需要获取参数
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        //需要执行SQL
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
        //需要封装结果集
        return null;
    }

    private BoundSql getBoundSql(String sql){
        //现在的问题是boundsql应该怎么写
        //boundsql有什么需求
        //select * from user where username = #{username} -> select * from user where username = ?;
        //和参数列表
        return null;
    }
}
