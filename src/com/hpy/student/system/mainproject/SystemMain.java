package com.hpy.student.system.mainproject;


import com.hpy.student.system.entity.Students;
import com.hpy.student.system.manager.StudentManager;
import com.hpy.student.system.util.MyFilter;

import java.util.Comparator;
import java.util.Scanner;

public class SystemMain {
    public static void main(String[] args) {
        StudentManager stm = new StudentManager();

        for (int i = 0; i < 10; i++) {
            String name = "彭于晏" + i;
            int age = (int) (Math.random() * 50);
            boolean gender = Math.random() > 0.5;
            String className = "JavaEE1904";
            float javaScore = (float) (Math.random() * 100);
            float cScore = (float) (Math.random() * 100);
            float phpScore = (float) (Math.random() * 100);
            float htmlScore = (float) (Math.random() * 100);

            stm.add(new Students(i, name, gender, className, age, javaScore, cScore, phpScore, htmlScore));
        }

        int choose = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("欢迎来到德莱联盟");
            System.out.println("1. 查看所有学生");
            System.out.println("2. 查看指定ID的学生");
            System.out.println("3. 添加新学生");
            System.out.println("4. 根据ID修改学生信息");
            System.out.println("5. 根据需求排序学生信息");
            System.out.println("6. 根据需求展示符合条件的学生信息");
            System.out.println("7. 根据ID删除学生");
            System.out.println("8. 退出");

            choose = sc.nextInt();
            sc.nextLine();

            switch (choose) {
                case 1:
                    stm.show();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    stm.sortUsingCompare(new Comparator<Students>() {
                        @Override
                        public int compare(Students stu1, Students stu2) {
                            return (int) ((stu1.getJavaScore() - stu2.getJavaScore()) * 100);
                        }
                    });
                    break;
                case 6:
                    stm.showInfoUsingMyFilter(new MyFilter() {
                        @Override
                        public boolean accept(Students stu) {
                            return stu.getJavaScore() > 50 && stu.getTotalScore() > 200;
                        }
                    });
                    break;
                case 7:
                    break;
                case 8:
                    break;
                default:
                    break;

            }
        }

    }
}

