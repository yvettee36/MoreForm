package cn.itcast.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yvettee on 2017/10/10.
 */
public class Student {
    private String id;
    private String name;
    private Set teachers = new HashSet();

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

    public Set getTeachers() {
        return teachers;
    }

    public void setTeachers(Set teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", teachers=" + teachers +
                '}';
    }
}
