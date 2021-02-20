package com.wsbo.week5.starter;

import lombok.Data;

import java.util.List;

/**
 * @author kimmking
 */
@Data
public class Klass {

    List<Student> students;

    public void dong() {
        System.out.println(this.getStudents());
    }

}
