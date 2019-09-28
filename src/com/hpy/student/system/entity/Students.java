package com.hpy.student.system.entity;

/**
 * 学生实体类
 *
 * @author hpy
 * @version v1.0
 * @since v1.0
 */
public class Students {
    private int id;
    private String name;
    private boolean gender;
    private String className;
    private int age;
    private float javaScore;
    private float cScore;
    private float phpScore;
    private float htmlScore;
    private float totalScore;
    private float rank;

    /**
     * 学生类的构造方法
     */
    public Students() {
    }

    /**
     * @param id        学生id号
     * @param name      学生姓名
     * @param gender    学生性别
     * @param className 学生班级
     * @param age       学生年龄
     * @param javaScore 学生java成绩
     * @param cScore    学生c语言成绩
     * @param phpScore  学生Php成绩
     * @param htmlScore 学生html成绩
     */
    public Students(int id, String name, boolean gender, String className, int age, float javaScore, float cScore, float phpScore, float htmlScore) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.className = className;
        this.age = age;
        this.javaScore = javaScore;
        this.cScore = cScore;
        this.phpScore = phpScore;
        this.htmlScore = htmlScore;
        this.totalScore = javaScore + htmlScore + phpScore + cScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(float javaScore) {
        //java成绩改变时要改变总成绩
        //将总成绩中java的减去
        totalScore -= this.javaScore;
        //使用参数传递的新的java成绩
        this.javaScore = javaScore;
        //将新的java成绩加到总成绩中
        totalScore += javaScore;
    }

    public float getcScore() {
        return cScore;
    }

    public void setcScore(float cScore) {
        totalScore -= this.cScore;
        this.cScore = cScore;
        totalScore += cScore;
    }

    public float getPhpScore() {
        return phpScore;
    }

    public void setPhpScore(float phpScore) {
        totalScore -= this.phpScore;
        this.phpScore = phpScore;
        totalScore += phpScore;
    }

    public float getHtmlScore() {
        return htmlScore;
    }

    public void setHtmlScore(float htmlScore) {
        totalScore -= this.htmlScore;
        this.htmlScore = htmlScore;
        totalScore += htmlScore;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", className='" + className + '\'' +
                ", age=" + age +
                ", javaScore=" + javaScore +
                ", cScore=" + cScore +
                ", phpScore=" + phpScore +
                ", htmlScore=" + htmlScore +
                ", totalScore=" + totalScore +
                ", rank=" + rank +
                '}';
    }
}
