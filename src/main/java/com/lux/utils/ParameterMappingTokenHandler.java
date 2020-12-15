package com.lux.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/15
 **/
public class ParameterMappingTokenHandler implements TokenHandler{
    private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();

    // context是参数名称 #{id} #{username}

    public String handleToken(String content) {
        parameterMappings.add(buildParameterMapping(content));
        return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
        ParameterMapping parameterMapping = new ParameterMapping(content);
        return parameterMapping;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

}
