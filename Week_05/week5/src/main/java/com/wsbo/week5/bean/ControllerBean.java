package com.wsbo.week5.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 项峥
 */
@Controller
public class ControllerBean {
    @Autowired
    private ServiceBean serviceBean;
}
