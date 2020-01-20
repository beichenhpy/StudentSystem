package com.hpy.student.system.util.impl;

import com.hpy.student.system.entity.Students;
import com.hpy.student.system.util.MyComparator;

public class MyComparatorImp implements MyComparator {

    @Override
    public int compare(Students stu1, Students stu2) {
        return (int) ((stu2.getTotalScore() - stu1.getTotalScore())*100);
    }
}
