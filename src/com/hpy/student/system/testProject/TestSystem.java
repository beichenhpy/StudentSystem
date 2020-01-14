package com.hpy.student.system.testProject;

import com.hpy.student.system.entity.Students;
import com.hpy.student.system.manager.StudentManager;

public class TestSystem {
    public static void main(String[] args) {

        Students students = new Students(1, "hpy", true, "计科161", 22, 76, 60, 8.6F, 87.5F);
        Students students1 = new Students(2, "hpy", true, "计科161", 22, 16, 60, 8.6F, 87.5F);
        Students students2 = new Students(3, "hpy", true, "计科161", 90, 90, 60, 8.6F, 87.5F);
        Students students3 = new Students(4, "hpy", true, "计科161", 22, 46, 60, 8.6F, 87.5F);
        Students students4 = new Students(5, "hpy", true, "计科161", 22, 56, 60, 8.6F, 87.5F);
        Students students5 = new Students(6, "hpy", true, "计科161", 22, 66, 60, 8.6F, 87.5F);
        Students students6 = new Students(7, "hpy", true, "计科161", 22, 26, 60, 8.6F, 87.5F);
        Students students7 = new Students(8, "hpy", true, "计科161", 22, 96, 60, 8.6F, 87.5F);
        Students students8 = new Students(9, "hpy", true, "计科161", 22, 26, 60, 8.6F, 87.5F);
        Students students9 = new Students(10, "hpy", true, "计科161", 22, 36, 60, 8.6F, 87.5F);
        Students students10 = new Students(11, "hpy", true, "计科161", 22, 36, 60, 8.6F, 87.5F);

        StudentManager studentManager = new StudentManager();
        studentManager.add(students);
        studentManager.add(students1);
        studentManager.add(students2);
        studentManager.add(students3);
        studentManager.add(students4);
        studentManager.add(students5);
        studentManager.add(students6);
        studentManager.add(students7);
        studentManager.add(students8);
        studentManager.add(students9);
        studentManager.add(students10);
        //studentManager.add(3, students11);
        //studentManager.delete(8);
        //studentManager.show();
        //studentManager.modify(1);
        //System.out.println(studentManager.get(1));
        //studentManager.sortByTotalScoreDesc();
        //studentManager.modify(1);
        //studentManager.show();
    }

}
