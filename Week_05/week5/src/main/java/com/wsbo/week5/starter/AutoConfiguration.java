package com.wsbo.week5.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author 项峥
 */
@Configuration
@EnableConfigurationProperties(Properties.class)
@ConditionalOnMissingBean(Student.class)
public class AutoConfiguration {
    @Autowired
    private Properties properties;

    @Bean
    public Student student() {
        Student student = new Student();
        student.setName(properties.getName());
        return student;
    }

    @Bean
    public Klass klass(Student student) {
        Klass klass = new Klass();
        klass.setStudents(Collections.singletonList(student));
        return klass;
    }

    @Bean
    public School school(Klass klass, Student student) {
        School school = new School();
        school.setClass1(klass);
        school.setStudent100(student);
        return school;
    }
}
