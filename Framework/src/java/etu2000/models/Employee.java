package etu2000.models;

import etu2000.framework.controllers.Url;
import java.util.Vector;

public class Employee {
    String id;
    String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee() {
    }

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Url("all-emp")
    public Vector<Employee> getAll(){
        Vector<Employee> all=new Vector<>();
        return all;
    }
    
    @Url("add-emp")
    public void addEmp(Employee emp){
        System.out.println("added");
    }
}
