package ua.goit.jdbc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.jdbc.model.EmployeeDao;
import ua.goit.jdbc.model.entities.Employee;

import java.util.List;

public class EmployeeController {
    private EmployeeDao employeeDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Employee searchByName(String name) {
        if (name == null) {
            throw new RuntimeException("Cannot search employee by NULL name");
        }

        return employeeDao.searchByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addEmployee(Employee employee) {
        if (employee != null) {
            int addedID = employeeDao.add(employee);
            LOGGER.info("Successfully added employee with ID " + addedID);
        } else {
            LOGGER.error("Trying to add NULL employee");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteEmployee(int id) {
        if (id > 0) {
            employeeDao.deleteByID(id);
            LOGGER.info("Successfully deleted employee with id " + id);
        } else {
            LOGGER.error("Cannot delete employee with negative ID");
        }
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setTxManager(PlatformTransactionManager txManager) { }
}
