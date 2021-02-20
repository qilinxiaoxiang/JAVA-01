package com.wsbo.week5.bean;

import org.springframework.context.annotation.*;

/**
 * @author 项峥
 */
@Configuration
public class AnnotationBean {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope("prototype")
    @Lazy
    @Primary
    public TargetBean targetBean() {
        return new TargetBean();
    }
}

class TargetBean {
    public void init() {
        System.out.println("Bean 初始化方法被调用");
    }

    public void destroy() {
        System.out.println("Bean 销毁方法被调用");
    }
}
