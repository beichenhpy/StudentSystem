package com.hpy.student.system.Dao;

import com.hpy.student.system.entity.Students;
import com.hpy.student.system.util.BaseDao;

import java.sql.SQLException;
import java.util.List;

/**
 * 单例类型
 */
public class StudentDao extends BaseDao {
    private static StudentDao sd = null;
    private StudentDao(){}
    public static StudentDao getInstance(){
        synchronized (StudentDao.class){
            if (null == sd){
                sd = new StudentDao();
            }
        }
        return sd;
    }

    /**
     * 把传入的Students对象传入数据库中
     * @param students 类对象
     * @return 返回受到影响的行数
     */
    public int add(Students students) throws SQLException {
        String sql = "insert into student(name, age, gender, classname, " +
                "javascore, cscore, phpscore, htmlscore, totalscore) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        Object[] parameter = {students.getName(),students.getAge(),students.isGender()
        ,students.getClassName(),students.getJavaScore(),students.getcScore(),students.getPhpScore()
        ,students.getHtmlScore(),students.getTotalScore()};
        return super.update(sql, parameter);

    }
    public List<Students> findAll() throws SQLException {
        String sql = "select * from student";

        return super.query(sql,null,Students.class);
    }
    public List<Students> findAllByRank() throws SQLException {
        String sql = "select * from student order by `rank` asc ";

        return super.query(sql,null,Students.class);
    }
    public int update(Students students) throws SQLException {
        Object[] parameter = null;
        String sql = "update student set name = ? ,age = ?,gender = ?,className = ?," +
                "javaScore = ?,cScore = ?,phpScore = ?,htmlScore = ?,totalScore = ?,`rank` = ? where id = ?";
            parameter = new Object[]{students.getName(), students.getAge(), students.isGender()
                    , students.getClassName(), students.getJavaScore(), students.getcScore(), students.getPhpScore()
                    , students.getHtmlScore(), students.getTotalScore(), students.getRank(),students.getId()};


        return super.update(sql, parameter);
    }
    public List<Students> findOne(int id) throws SQLException{
        String sql = "select * from student where id = ?";

        return super.query(sql,new Object[]{id},Students.class);
    }
    public int delete(int id) throws SQLException {
        String sql = "delete from student where id = ?";
        return super.update(sql,new Object[]{id});
    }
}
