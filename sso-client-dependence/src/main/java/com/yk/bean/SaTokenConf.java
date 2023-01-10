package com.yk.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jzx
 */
@Component
//@ConfigurationProperties(prefix = "SaTokenConf")
public class SaTokenConf {
    @Value("${SaTokenConf.addInclude}")
    private String addInclude;
    @Value("${SaTokenConf.addExclude}")
    private String addExclude;

    public String getAddInclude() {
        return addInclude;
    }

    public void setAddInclude(String addInclude) {
        this.addInclude = addInclude;
    }

    public String getAddExclude() {
        return addExclude;
    }

    public void setAddExclude(String addExclude) {
        this.addExclude = addExclude;
    }
}
