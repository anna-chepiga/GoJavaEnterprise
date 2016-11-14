package ua.goit.jdbc.model.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.jdbc.model.EmployeeDao;
import ua.goit.jdbc.model.entities.Employee;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEmployeeDao implements EmployeeDao {
    private DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    @Override
    @Transactional
    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");

            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet);
                result.add(employee);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Employee searchByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE first_name=?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return createEmployee(resultSet);
            } else {
                throw new RuntimeException("Cannot find person with name " + name);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public int add(Employee employee) {
        int addedID;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO employee " +
                     "(first_name, last_name, birthday, phone_number, position, salary)" +
                     "VALUES (?,?,?,?,?,?)")) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getBirthday());
            statement.setString(4, employee.getPhoneNumber());
            statement.setInt(5, employee.getPosition());
            statement.setInt(6, employee.getSalary());

            statement.executeUpdate();

            addedID = getMaxID();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return addedID;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteByID(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setBirthday(resultSet.getString("birthday"));
        employee.setPhoneNumber(resultSet.getString("phone_number"));
        employee.setPosition(resultSet.getInt("position"));
        employee.setSalary(resultSet.getInt("salary"));
        return employee;
    }

    private int getMaxID() {
        int maxID = 0;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM employee");

            if (resultSet.next()) {
                maxID = resultSet.getInt("MAX(id)");
            }
        } catch (SQLException e) {
            LOGGER.error("Exception occurred while connecting to DB", e);
            throw new RuntimeException(e);
        }

        return maxID;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}