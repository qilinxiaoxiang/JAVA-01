package com.wsbo.week5.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * @author 项峥
 */
@Repository
public class RepositoryBean {
    @Autowired
    private ControllerBean controllerBean;
    @Autowired
    private ServiceBean serviceBean;
    @Autowired
    private RepositoryBean repositoryBean;
    @Value("bean.config")
    private String config;
}
