package com.lux.sqlsession;

import java.util.List;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/15
 **/
public interface SqlSession {
    //查询所有
    public <E> List<E> selectList(String statementId, Object... params) throws Exception;

    //根据条件查询单个
    public <T> T selectOne(String statementId,Object... params) throws Exception;

    //为Dao接口生成代理实现类
    public <T> T getMapper(Class<?> mapperClass);
}
