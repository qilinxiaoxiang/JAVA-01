package com.wsbo.week5.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 项峥
 */
@Service
public class ServiceBean {
    @Autowired
    private RepositoryBean repositoryBean;
}
