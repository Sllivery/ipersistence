package com.lux.sqlsession;

import com.lux.pojo.Configuration;
import com.lux.pojo.MappedStatement;

import java.util.List;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/15
 **/
public class DefaultSqlSession implements SqlSession{

    /**
     * 问题是configuration和executor生命周期是不一样的 configuration可以说
     * 贯穿在整个运行的时候.(这里可以引申思考怎么看一个架构图 位置在上面的靠近api,位置在下面的是调用底层,位置在侧面的是辅助)
     *
     * executor应该在哪里初始化,放在对象层面还是方法层面
     */
    private Configuration configuration;
    //private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        //将要去完成对simpleExecutor里的query方法的调用
        Executor executor = new SimpleExecutor(configuration);
        MappedStatement mappedStatement = configuration.getStatementMap().get(statementId);
        List<Object> list = executor.query(configuration, mappedStatement, params);

        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if(objects.size()==1){
            return (T) objects.get(0);
        }else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        return null;
    }
}
