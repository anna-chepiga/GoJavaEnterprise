package ua.goit.jdbc.model;

import ua.goit.jdbc.model.entities.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getAll();

    Employee searchByName(String name);

    int add(Employee employee);

    void deleteByID(int id);
}
