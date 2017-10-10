package cn.itcast.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yvettee on 2017/10/10.
 */
public class Teacher {
    private String id;
    private String name;
    private double salary;
    private Set students = new HashSet();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Set getStudents() {
        return students;
    }

    public void setStudents(Set students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", students=" + students +
                '}';
    }
}
