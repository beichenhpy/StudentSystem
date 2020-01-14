package com.hpy.student.system.mainproject;


import com.hpy.student.system.entity.Students;
import com.hpy.student.system.manager.StudentManager;
import com.hpy.student.system.util.MyFilter;

import java.util.Comparator;
import java.util.Scanner;

public class SystemMain {
    public static void main(String[] args) {
        StudentManager stm = new StudentManager();

        for (int i = 0; i < 5; i++) {
            String name = "彭于晏" + i;
            int age = (int) (Math.random() * 50);
            boolean gender = Math.random() > 0.5;
            String className = "JavaEE1904";
            float javaScore = (float) (Math.random() * 100);
            float cScore = (float) (Math.random() * 100);
            float phpScore = (float) (Math.random() * 100);
            float htmlScore = (float) (Math.random() * 100);
            Students students = new Students();
            students.setId(i);
            students.setName(name);
            students.setAge(age);
            students.setGender(gender);
            students.setHtmlScore(htmlScore);
            students.setPhpScore(phpScore);
            students.setJavaScore(javaScore);
            students.setClassName(className);
            students.setcScore(cScore);
            stm.add(students);
            stm.sortByTotalScoreDesc();
        }



        int choose = 0;
        boolean flag = false;
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
                    System.out.println("请输出要查询学生Id");
                    System.out.println(stm.get(sc.nextInt()));
                    break;
                case 3:
                    System.out.println("输入学生的基本信息，按照id,姓名,性别,班级,年龄,java成绩,c成绩,php成绩,html成绩,按回车继续");
                    Students students = new Students();
                    students.setId(sc.nextInt());
                    students.setName(sc.next());
                    String gender = sc.next();
                    if (!("男".equals(gender) ||"女".equals(gender))){
                        flag = true;
                    }
                    while (flag) {
                        System.out.println("重新输入男女");
                        String gender1 = sc.next();
                        if ("男".equals(gender1) ||"女".equals(gender1)){
                            flag = false;
                        }
                    }
                    if ("男".equals(gender)){
                        students.setGender(true);
                    }else {
                        students.setGender(false);
                    }
                    students.setClassName(sc.next());
                    students.setAge(sc.nextInt());
                    students.setJavaScore(sc.nextFloat());
                    students.setPhpScore(sc.nextFloat());
                    students.setHtmlScore(sc.nextFloat());
                    students.setcScore(sc.nextFloat());
                    stm.add(students);
                    stm.sortByTotalScoreDesc();

                    break;
                case 4:
                    System.out.println("输入学生id");
                    stm.modify(sc.nextInt());
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
                    System.out.println("输入学生id");
                    stm.delete(sc.nextInt());
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    break;

            }
        }

    }
}

