package com.lux.sqlsession;

import com.lux.pojo.Configuration;
import com.lux.pojo.MappedStatement;

import java.util.List;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/15
 **/
public interface Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}
