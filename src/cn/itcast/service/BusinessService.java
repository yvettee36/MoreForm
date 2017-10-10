package cn.itcast.service;

import cn.itcast.dao.DepartmentDao;
import cn.itcast.dao.TeacherDao;
import cn.itcast.domain.Department;
import cn.itcast.domain.Employee;
import cn.itcast.domain.Student;
import cn.itcast.domain.Teacher;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by yvettee on 2017/10/10.
 */
public class BusinessService {
    @Test
    public void add() {
        Department d = new Department();
        d.setId("111");
        d.setName("开发部");

        Employee e1 = new Employee();
        e1.setId("1");
        e1.setName("aa");
        e1.setSalary(10000);


        Employee e2 = new Employee();
        e2.setId("2");
        e2.setName("bb");
        e2.setSalary(10000);

        d.getEmployees().add(e1);
        d.getEmployees().add(e2);

        DepartmentDao dao = new DepartmentDao();
        dao.add(d);
    }

    @Test
    public void find() throws SQLException {
        DepartmentDao dao = new DepartmentDao();
        Department d = dao.find("111");
        System.out.println(d);
    }

    @Test
    public void delete() throws SQLException {
        DepartmentDao dao = new DepartmentDao();
        dao.delete("111");
    }

    @Test
    public void addTeacher() throws SQLException {
        Teacher t = new Teacher();
        t.setId("1");
        t.setName("陈陈");
        t.setSalary(10000);

        Student s1 = new Student();
        s1.setId("1");
        s1.setName("aaa");

        Student s2 = new Student();
        s2.setId("2");
        s2.setName("bbb");

        t.getStudents().add(s1);
        t.getStudents().add(s2);

        TeacherDao dao = new TeacherDao();
        dao.add(t);
    }

    @Test
    public void findTeacher() throws SQLException {
        TeacherDao dao = new TeacherDao();
        Teacher t = dao.find("1");
        System.out.println(t.getId());
        System.out.println(t.getStudents());
    }
}
