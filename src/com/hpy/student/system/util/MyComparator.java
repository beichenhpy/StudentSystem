package com.hpy.student.system.util;

import com.hpy.student.system.entity.Students;

public interface MyComparator {
    /**
     * MyComparator 规定的方法，该方法的返回值是int类型
     *
     * @param stu1 Student类型
     * @param stu1 Student类型
     * @return > 0 前者大于后者，< 0 前者小于后者 == 0 两者相同
     */
    public int compare(Students stu1, Students stu2);
}