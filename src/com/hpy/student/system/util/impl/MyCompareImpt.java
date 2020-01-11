package com.hpy.student.system.util.impl;

import com.hpy.student.system.entity.Students;
import com.hpy.student.system.util.MyCompare;

public class MyCompareImpt implements MyCompare {
    /**
     * MyCompare规定的方法，该方法返回值类型为int 类型
     * @param stu1 students类型
     * @param stu2 students类型
     * @param flag int类型 1为降序，其他为升序 默认升序
     * @return 前者大于后者 >0 前者小于后者 <0 前者等于后者 ==0
     */
    @Override
    public int compare(Students stu1, Students stu2,int flag ) {
        if (flag == 1){
            return (int)((stu2.getTotalScore() - stu1.getTotalScore()) * 100);
        }
            return (int) ((stu1.getTotalScore() - stu2.getTotalScore()) * 100);
    }
}
