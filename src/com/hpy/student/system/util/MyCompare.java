package com.hpy.student.system.util;

import com.hpy.student.system.entity.Students;

public interface MyCompare {
    /**
     * MyCompare规定的方法，该方法返回值类型为int 类型
     * @param stu1 students类型
     * @param stu2 students类型
     * @param flag int类型 1为降序，其他为升序
     * @return 前者大于后者 >0 前者小于后者 <0 前者等于后者 ==0
     */
    public int compare(Students stu1, Students stu2,int flag);
}
