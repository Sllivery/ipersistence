package com.lux.config;

import com.lux.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/11
 **/
public class XMLConfig {

    private Configuration configuration;

    public XMLConfig() {
        this.configuration = new Configuration();
    }

    public Configuration parse (InputStream in) throws Exception {
        Document document = new SAXReader().read(in);
        //遍历config文件,将数据库配置到datasource里面
        Element configurationRoot = document.getRootElement();//根节点
        buildDatasource(configurationRoot);
        //获取到config文件的mapper节点,读取mapper的resource属性
        buildMappedStatement(configurationRoot);
        return configuration;
    }

    //读取config文件property,封装成datasource
    private void buildDatasource(Element root) throws PropertyVetoException {
        List<Element> list = root.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("user"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        //封装进configuration
        this.configuration.setDataSource(comboPooledDataSource);
    }

    //读取config文件中的mapper,每个mapper解析一遍
    private void buildMappedStatement(Element root) throws Exception {
        List<Element> list = root.selectNodes("//mapper");
        //获取所有的mapper送进另一个对象封装
        for (Element element : list) {
            String mapperPath = element.attributeValue("resource");
            MappedConfig mappedConfig = new MappedConfig(this.configuration);
            mappedConfig.parse(mapperPath);
        }
    }
}
