package com.hpy.student.system.mainproject;


import com.hpy.student.system.entity.Students;
import com.hpy.student.system.Controler.StudentManager;
import com.hpy.student.system.util.MyFilter;
import com.hpy.student.system.util.impl.MyComparatorImp;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.Scanner;

public class SystemMain {
    public static void main(String[] args) throws SQLException {
        StudentManager stm = new StudentManager();

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
                    Students students1 = stm.get(sc.nextInt());
                    if (null != students1){
                        System.out.println(students1);
                    }
                    break;
                case 3:
                    System.out.println("输入学生的基本信息，按照,姓名,性别,班级,年龄,java成绩,c成绩,php成绩,html成绩,按回车继续");
                    Students students = new Students();
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

                    break;
                case 4:
                    System.out.println("输入学生id");
                    stm.modify(sc.nextInt());
                    break;
                case 5:
                    stm.sortUsingCompare(new MyComparatorImp());
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

