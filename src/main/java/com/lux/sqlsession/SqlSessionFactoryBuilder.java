package com.lux.sqlsession;

import com.lux.config.XMLConfig;
import com.lux.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/11
 **/
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) throws Exception {
        //需要解析inputstream,将其中的节点添加进容器
        XMLConfig xmlConfig = new XMLConfig();
        Configuration configuration = xmlConfig.parse(inputStream);
        return new DefaultSqlSessionFactory(configuration);
    }
}
