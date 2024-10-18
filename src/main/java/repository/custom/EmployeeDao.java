package repository.custom;

import entity.Employee;
import javafx.collections.ObservableList;
import repository.CrudDao;

import java.util.List;

public interface EmployeeDao extends CrudDao<Employee> {
    ObservableList<String> getAllEmployeeIds();
}
