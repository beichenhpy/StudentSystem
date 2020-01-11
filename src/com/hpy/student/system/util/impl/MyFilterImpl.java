package com.hpy.student.system.util.impl;

import com.hpy.student.system.entity.Students;
import com.hpy.student.system.util.MyFilter;

public class MyFilterImpl implements MyFilter {

    @Override
    public boolean accept(Students stu) {
        return stu.getAge() > 16 && stu.getJavaScore() > 60;
    }
}
