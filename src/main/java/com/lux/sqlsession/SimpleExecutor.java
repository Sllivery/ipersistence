package com.lux.sqlsession;

import com.lux.pojo.Configuration;
import com.lux.pojo.MappedStatement;
import com.lux.utils.GenericTokenParser;
import com.lux.utils.ParameterMapping;
import com.lux.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
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
        //设置参数
        String paramterType = mappedStatement.getParaClass();
        Class<?> paramtertypeClass = getClassType(paramterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParams();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            //反射
            Field declaredField = paramtertypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i+1,o);

        }
        // 5. 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultClass();
        Class<?> resultTypeClass = getClassType(resultType);
        ArrayList<Object> objects = new ArrayList<>();
        // 6. 封装返回结果集
        while (resultSet.next()){
            Object o = resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 字段名
                String columnName = metaData.getColumnName(i);
                // 字段的值
                Object value = resultSet.getObject(columnName);
                //使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,value);
            }
            objects.add(o);
        }
        return (List<E>) objects;
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if(paramterType!=null){
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;
    }

    private BoundSql getBoundSql(String sql){
        //现在的问题是boundsql应该怎么写
        //boundsql有什么需求
        //select * from user where username = #{username} -> select * from user where username = ?;
        //和参数列表
        //标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析出来的sql
        String parseSql = genericTokenParser.parse(sql);
        //#{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql,parameterMappings);
        return boundSql;
    }
}
