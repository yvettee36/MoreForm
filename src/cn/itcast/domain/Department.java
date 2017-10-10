package cn.itcast.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yvettee on 2017/10/10.
 */
public class Department {
    private String id;
    private String name;
    private Set employees = new HashSet();

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

    public Set getEmployees() {
        return employees;
    }

    public void setEmployees(Set employees) {
        this.employees = employees;
    }
}
