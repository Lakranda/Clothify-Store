package service.custom;

import dto.EmployeeDTO;
import entity.Employee;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface EmployeeService extends SuperService {
    boolean addEmployee(EmployeeDTO employee);
    ObservableList<Employee> getAllEmployees();
    ObservableList<String> getAllEmployeeIds();

}
