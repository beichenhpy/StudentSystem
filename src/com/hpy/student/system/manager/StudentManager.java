package com.hpy.student.system.manager;

/*
    管理类：
    学生数组用来保存学生对象

    增删改查，排序，过滤
 */

import com.hpy.student.system.entity.Students;
import com.hpy.student.system.util.MyCompare;
import com.hpy.student.system.util.MyFilter;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author hpy
 * @version v1.0
 * @since V1.0
 */
public class StudentManager {
    /**
     * 准备一个学生类对象数组的引用，用于保存在调用构造方法时，创建学生数组
     * 内存堆区的空间首地址
     */
    private Students[] allStudents = null;
    /**
     * DEFAULT_CAPACITY 调用无参构造方法时
     * 默认的Student 类对象数组初始化容量
     * private修饰防止类外使用
     * static修饰是让这个数据在加载时执行
     * final修饰实际上就是带有名字的常量
     * int类型是因为数组容量本身是int范围内的
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * StudentManager类的无参构造方法，这里默认的数组容量创建
     * Student类对象数组
     */
    public StudentManager() {
        allStudents = new Students[DEFAULT_CAPACITY];
    }

    /**
     * 带参数的构造方法，用于用户初始化数组的容量
     *
     * @param initCapacity 要求数据大于等于0，如果不满足默认使用默认容量
     */
    public StudentManager(int initCapacity) {
        if (initCapacity <= 0) {
            allStudents = new Students[DEFAULT_CAPACITY];
        } else {
            allStudents = new Students[initCapacity];
        }
    }

    /**
     * 表示当前StudentManager底层数组中，保存的有效元素个数是多少
     */
    private int size = 0;

    /*
        添加方法，方法分析

        方法名： add
        形式参数列表：
            这里需要学生【类对象】
            Students stu
        返回值类型：
            boolean 添加成功返回true,失败返回false
        public boolean add(Students stu)

       添加用户需要尾插法：
            需要一个计数，选择什么变量
            1.局部变量：不行运行完销毁，不易保存记录
            2.成员变量：不销毁，始终存在，可以选择-->【最好选择】
            3.静态变量：保存在内存数据区的变量，可以提供所有类对象使用，可以通过类名调用
            size定义一个静态成员变量，会导致其他程序使用出现问题
        问题：
            StudentManager 底层数组容量为10，极有可能数组容量不足
            我们需要对数组进行扩容，但是java中的语法规定，数组容量一旦定义无法更改
        解决问题：
            这里需要一个新数组，数据拷贝，保存新数组地址
     */

    /**
     * 添加学生类对象的方法，采用尾插法
     *
     * @param stu 学生类对象
     * @return boolean 添加成功返回true,失败返回false
     */


    public boolean add(Students stu) {
        //参数合法性判断
        if (stu == null) {
            System.out.println("Input Parameter is invalid");
            return false;
        }
        //当长度等于容量，需要扩容，扩容最小为容量+1
        if (allStudents.length == size) {
            grow(size + 1);
        }
        allStudents[size++] = stu;
        return true;
    }
    /*
    完成一个指定下标位置添加数据的方法
    指定下标位置，数据是一个Stduent对象
    方法分析：
        方法名：add
        形式参数列表：
            1.下标位置：int index 大于0 小于size(有效元素个数）
            2.指定数据 Students students
        返回值类型 bool 成功返回true 失败返回false
        权限修饰 public
     */

    /**
     * 完成一个指定下标位置(非空的位置）添加数据的方法
     *
     * @param index    执行的下标位置 大于0 小于size(有效元素个数）
     * @param students Students students
     * @return bool 成功返回true 失败返回false
     */
    public boolean add(int index, Students students) {
        //参数合理性
        if (index < 0 || index > size || null == students) {
            System.out.println("Input Parameter is invalid");
            return false;
        }
        //判断容量
        if (allStudents.length == size) {
            grow(size + 1);
        }
        //插入
        for (int i = size; i < index; i++) {
            allStudents[i] = allStudents[i - 1];
        }
        allStudents[index] = students;
        //数据添加需要又要元素个数加赋值1
        size += 1;
        return true;
    }
    /*
    完成对指定下标位置的删除数据的方法
    方法分析：
        1.方法名。delete
        2.形式参数列表，int index 大于0 小于size  Students students
        3.返回值类型，bool 删除成功返回true 删除失败返回false
        4.权限修饰 public
     */

    /**
     * 完成对指定学号的删除数据的方法
     *
     * @param id int index 大于0 小于size
     * @return bool 删除成功返回true 删除失败返回false
     */
    public boolean delete(int id) {
        //从下标为0开始，到有效元素，不能使用数组容量
        int index = findIndexById(id);
        if (index == -1) {
            System.out.println("查无此人");
        }
        for (int i = index; i < size - 1; i++) {
            allStudents[i] = allStudents[i + 1];
        }
        allStudents[size - 1] = null;
        size--;
        return true;
    }
    /*
    修改：修改学生信息
    方法分析：
            方法名：modify
            形式参数列表 学生id
            返回值类型 ；修改成功true 修改失败false
            权限修饰：public
     */

    /**
     * 修改方法，通过学生id号修改学生信息
     *
     * @param studentId int类型
     * @return 修改成功true 修改失败false
     */
    public boolean modify(int studentId) {
        //从下标为0开始，到有效元素，不能使用数组容量
        int index = findIndexById(studentId);
        if (index == -1) {
            System.out.println("查无此人");
            return false;
        }

        Students student = allStudents[index];
        int choice = 0;
        boolean flag = false;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("修改" + student.getName() + "的信息");
            System.out.println("学生id:" + student.getId());
            System.out.println("学生年龄：" + student.getAge() + "  学生性别：" + (student.isGender() ? '男' : '女') + "  班级：" + student.getClassName());
            System.out.println("JAVA成绩：" + student.getJavaScore() + "  c成绩：" + student.getcScore() + "  PHP成绩：" + student.getPhpScore());
            System.out.println("HTML成绩：" + student.getHtmlScore() + "  总成绩：" + student.getTotalScore() + "  rank:" + student.getRank());
            System.out.println("1.修改学生姓名");
            System.out.println("2.修改学生年龄");
            System.out.println("3.修改学生性别");
            System.out.println("4.修改学生班级");
            System.out.println("5.修改学生Java成绩");
            System.out.println("6.修改学生c成绩");
            System.out.println("7.修改学生PHP成绩");
            System.out.println("8.修改学生html成绩");
            System.out.println("9.退出");
            choice = scanner.nextInt();
            //请在获取后加入nextline()
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("输入学生新姓名：");
                    String name = scanner.nextLine().trim();
                    if (name.length() == 0) {
                        System.out.println("输入姓名有误，修改失败");
                        name = student.getName();
                    }
                    student.setName(name);
                    break;
                case 2:
                    System.out.println("输入学生新年龄");
                    int age = scanner.nextInt();
                    if (age <= 0) {
                        System.out.println("输入有误，修改失败");
                        age = student.getAge();
                    }
                    student.setAge(age);
                    break;
                case 3:
                    System.out.println("输入新性别：男/女");
                    char gender = scanner.nextLine().charAt(0);
                    if (gender == '男' || gender == '女') {
                        student.setGender(gender == '男');
                    } else {
                        System.out.println("输入有误,修改失败");
                    }
                    break;
                case 4:
                    System.out.println("输入新的班级");
                    String className = scanner.nextLine().trim();
                    if (className.length() == 0) {
                        System.out.println("输入有误，修改失败");
                        className = student.getClassName();
                    }
                    student.setClassName(className);
                    break;
                case 5:
                    System.out.println("输入新的成绩");
                    float javaScore = scanner.nextFloat();
                    if (javaScore < 0 || javaScore > 100) {
                        System.out.println("输入有误，修改失败");
                        javaScore = student.getJavaScore();
                    }
                    student.setJavaScore(javaScore);
                    break;
                case 6:
                    System.out.println("输入新的成绩");
                    float cScore = scanner.nextFloat();
                    if (cScore < 0 || cScore > 100) {
                        System.out.println("输入有误，修改失败");
                        cScore = student.getcScore();
                    }
                    student.setcScore(cScore);
                    break;
                case 7:
                    System.out.println("输入新的成绩");
                    float phpScore = scanner.nextFloat();
                    if (phpScore < 0 || phpScore > 100) {
                        System.out.println("输入有误，修改失败");
                        phpScore = student.getPhpScore();
                    }
                    student.setPhpScore(phpScore);
                    break;
                case 8:
                    System.out.println("输入新的成绩");
                    float htmlScore = scanner.nextFloat();
                    if (htmlScore < 0 || htmlScore > 100) {
                        System.out.println("输入有误，修改失败");
                        htmlScore = student.getHtmlScore();
                    }
                    student.setHtmlScore(htmlScore);
                    break;
                case 9:
                    System.out.println("欢迎下次使用");
                    flag = true;
                    break;
                default:
                    break;
            }
            if (flag) {
                System.out.println("再见！");
                break;
            }
        }
        return true;
    }
    /*
    根据学生id获取学生对象
    方法分析：
        方法名：get
        形式参数列表：int studentId
        返回值类型：Student student / null
        修饰 public
    */

    /**
     * 根据学生Id号获取学生信息
     * @param studentId 学生id号
     * @return 找到返回学生对象 找不到返回null
     */
    public Students get(int studentId) {
        int index = findIndexById(studentId);
        return index >= 0 ? allStudents[index] : null;
    }
    /*
    封装根据学生id转换下标位置

     */
    /*
    方法分析：
        按照总分降序排序
        方法名：sortByTotalScoreDesc
        形式参数列表：不需要
        返回值不需要
        权限修饰符：public
        public void sortByTotalScoreDesc()
     */
    /**
     * 根据学生的总分排序，降序
     * 不允许对原始数据进行操作
     */
    public void sortByTotalScoreDesc(){
        //1.创建一个新数组，将原数据取出进行排序
        Students[] sortTemp = Arrays.copyOf(allStudents, size);
        //2.选择排序算法
        for (int i = 0; i < size - 1; i++) {
            int index = i;
            for (int j = i + 1; j < size; j++) {
                if (sortTemp[index].getTotalScore() < sortTemp[j].getTotalScore()){
                    index  = j;
                }
            }if (index != i){
                Students temp = sortTemp[index];
                sortTemp[index] = sortTemp[i];
                sortTemp[i] = temp;
            }
        }
        //3.展示数据
        for (Students students : sortTemp) {
            System.out.println(students);
        }
    }

    /**
     * 排序算法，使用一个自定义比较器接口实现作为方法的参数
     * @param com MyCompare 接口的实现类对象
     * @param flag int类型 1为降序，其他为升序
     */
    public void sortUsingCompare(MyCompare com,int flag){
        //1.创建一个新数组，将原数据取出进行排序
        Students[] sortTemp = Arrays.copyOf(allStudents, size);
        //2.选择排序算法
        for (int i = 0; i < size - 1; i++) {
            int index = i;
            for (int j = i + 1; j < size; j++) {
                if (com.compare(sortTemp[index],sortTemp[j],flag) > 0){
                    index  = j;
                }
            }if (index != i){
                Students temp = sortTemp[index];
                sortTemp[index] = sortTemp[i];
                sortTemp[i] = temp;
            }
        }
        //3.展示数据
        for (Students students : sortTemp) {
            System.out.println(students);
        }
    }

    /**
     * 展示使用自定义过滤器的显示学生信息
     * @param filter 自定义过滤器接口
     */
    public void showInfoUsingMyFilter(MyFilter filter){
        for (int i = 0; i < size; i++) {
            if (filter.accept(allStudents[i])){
                System.out.println(allStudents[i]);
            }
        }
    }
    /**
     * 学生id转换下标位置
     *
     * @param studentId int类型 学生id
     * @return 返回学生id对应下标
     */
    private int findIndexById(int studentId) {
        if (studentId < 0 || studentId > size) {
            System.out.println("Input Parameter is invalid");
            return -1;
        }
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (allStudents[i].getId() == studentId) {
                index = i;
                break;
            }
        }
        return index;
    }
    /*
    数组扩容，方法分析:
        方法名：
            grow
        形式参数列表：
            调用者告诉我们这里需要的最小容量
        返回值类型：
            void
        权限问题：
            public修饰：
                有隐患，可以在类外直接调用
                该方法实际通过add方法发现数组容量不足，自动调用的方法，和类外无关
            private修饰：
                保证该方法的安全性，有且只能在类内使用，约束了用户的操作
     */

    /**
     * 这里是数组的扩容方法，是当数组容量不足时自动调用，传入参数是要求的最小容量
     *
     * @param minCapacity int 类型 要求的最小容量是多少
     */
    private void grow(int minCapacity) {
        //1.获取原数组容量
        int oldCapacity = allStudents.length;
        //2.计算获得新数组容量,新数组容量约为是原数组容量的 1.5倍
        //oldCapacity >> 1 数据按照二进制向右移动一位 相当于/2 效率略高于/2
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //3.判断
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        //创建新数组
        Students[] temp = new Students[newCapacity];
        //复制内容到新数组中
        for (int i = 0; i < allStudents.length; i++) {
            temp[i] = allStudents[i];
        }
        //复制到旧数组中
        allStudents = temp;
    }

    //show
    public void show() {
        for (Students student : allStudents) {
            System.out.println(student);
        }
    }

}
