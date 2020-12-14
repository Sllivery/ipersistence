package com.lux.config;

import com.lux.io.Resources;
import com.lux.pojo.Configuration;
import com.lux.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/14
 **/
public class MappedConfig {
    private Configuration configuration;
    public MappedConfig(Configuration configuration){
        this.configuration = configuration;
    }
    public void parse(String mapperPath) throws DocumentException {
        InputStream inputStream = Resources.getResourceAsStream(mapperPath);
        Document document = new SAXReader().read(inputStream);
        Element root = document.getRootElement();
        buildMappedStatement(root);
    }
    //配置MappedStatement,填进Configuration
    private void buildMappedStatement(Element root){
        List<Element> selectNodes = root.selectNodes("//select");
        if(selectNodes.size() > 0){
            buildMappedStatement(root, selectNodes);
        }
        List<Element> updateNodes = root.selectNodes("//update");
        if(updateNodes.size() > 0 ){
            buildMappedStatement(root, updateNodes);
        }
        List<Element> deleteNodes = root.selectNodes("//delete");
        if(deleteNodes.size() > 0){
            buildMappedStatement(root, deleteNodes);
        }
    }

    private void buildMappedStatement(Element root, List<Element> nodes){
        String nameSpace = root.attributeValue("namespace");
        for (Element node : nodes) {
            String id = node.attributeValue("id");
            String resultType = node.attributeValue("resultType");
            String paramType = node.attributeValue("paramType");
            String sql = node.getText();
            MappedStatement mappedStatement = new MappedStatement(nameSpace+id, resultType, paramType, sql);
            Map<String, MappedStatement> statementMap = configuration.getStatementMap();
            statementMap.put(nameSpace + id, mappedStatement);
        }
    }
}
