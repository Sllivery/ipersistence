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
    /**
     * 这个方法可以好好看一下
     * 问题一:泛型 格式记住
     * 问题二:class对象和proxy创建实例的异同
     * 问题三:Proxy.newInstance是创建哪个参数的代理对象 是Class[] interfaces这个参数.
     *  所以创建完代理对象后,调用代理对象的某个方法,可以获取到它的方法名
     */
    public <T> T getMapper(Class<?> mapperClass) {
        //这个方法的目的是:通过获取代理对象的方法名,调用对应的sqlSession的方法
        //但是有个问题:sqlSession的方法并不能完全覆盖自己写的Mapper对象的方法啊
        return null;
    }
}
