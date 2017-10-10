package cn.itcast.dao;

import cn.itcast.domain.Department;
import cn.itcast.domain.Employee;
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
public class DepartmentDao {
    public void add(Department d) {
        try {
            QueryRunner runner = new QueryRunner(JdbcUtils_dbcp.getDataSource());
            //1.把department对象的数据插入到department表
            String sql = "insert into department(id,name) values(?,?)";
            Object params[] = {d.getId(), d.getName()};
            runner.update(sql, params);

            //2.把department对象中维护的所有员工插入到员工表
            //3.更新员工表的外键列，说明员工的部门
            Set<Employee> set = d.getEmployees();

            for (Employee e : set) {
                sql = "insert into employee(id,name,salary,department_id) values(?,?,?,?)";
                params = new Object[]{e.getId(), e.getName(), e.getSalary(), d.getId()};
                runner.update(sql, params);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Department find(String id) throws SQLException {

        QueryRunner runner = new QueryRunner(JdbcUtils_dbcp.getDataSource());

        //1.找部门表，查出部门的基本信息
        String sql = "select * from department where id=?";
        Department d = (Department) runner.query(sql, id, new BeanHandler(Department.class));

        //2.找员工表，找出部门下面所有员工
        sql = "select * from employee where department_id=?";
        List list = (List) runner.query(sql, id, new BeanListHandler(Employee.class));

        d.getEmployees().addAll(list);
        return d;
    }

    //删除时有外键，采用级联删除，删除时，将外键列置为空，修改表
    public void delete(String id) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtils_dbcp.getDataSource());
        String sql= "delete from department where id=?";
        runner.update(sql, id);
    }
}
