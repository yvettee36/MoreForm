package cn.itcast.dao;

import cn.itcast.domain.Student;
import cn.itcast.domain.Teacher;
import cn.itcast.utils.JdbcUtils_dbcp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by yvettee on 2017/10/10.
 */
public class TeacherDao {


    public void add(Teacher t) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils_dbcp.getDataSource());
        //1.取出老师存老师表
        String sql = "insert into teacher(id,name,salary) values(?,?,?)";
        Object params[] = {t.getId(), t.getName(), t.getSalary()};
        runner.update(sql, params);

        //2.取出老师所有学生的数据，存学生表
        Set<Student> set = t.getStudents();
        for (Student s : set) {
            sql = "insert into student(id,name) values(?,?)";
            params = new Object[]{s.getId(), s.getName()};
            runner.update(sql, params);

            //3.更新中间表，说明老师和学生的关系
            sql = "insert into teacher_student(teacher_id,student_id) values(?,?)";
            params = new Object[]{t.getId(), s.getId()};
            runner.update(sql, params);
        }
    }

    public Teacher find(String id) throws SQLException {
        QueryRunner runner = new QueryRunner(JdbcUtils_dbcp.getDataSource());
        //1.找老师表，找出老师的基本信息
        String sql = "select * from teacher where id=?";
        Teacher t = runner.query(sql, id, new BeanHandler<Teacher>(Teacher.class));

        //2.找出老师所有学生
        sql = "select * from teacher_student ts,student s where ts.teacher_id=? and ts.teacher_id=s.id";
        List<Student> list = runner.query(sql, id, new BeanListHandler<Student>(Student.class));

        t.getStudents().addAll(list);
        return t;
    }
}
