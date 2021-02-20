package com.wsbo.week5.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author
 */
@ConfigurationProperties(prefix = "student")
public class Properties {
    private String name = "项峥";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
