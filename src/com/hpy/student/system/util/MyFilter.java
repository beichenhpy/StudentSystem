package com.hpy.student.system.util;

import com.hpy.student.system.entity.Students;

public interface MyFilter {
    /**
     * 当前方法是自定义过滤器，用于过滤学生信息是否满足我们的要求
     * @param stu Students类型
     * @return 满足返回 true 不满足 false
     */
    public boolean accept(Students stu);
}
