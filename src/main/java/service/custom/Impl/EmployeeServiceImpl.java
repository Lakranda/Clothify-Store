package service.custom.Impl;

import dto.EmployeeDTO;
import entity.Employee;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import util.DaoType;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employeeDao=DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);



    @Override
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        System.out.println("pre "+employeeDTO);
        Employee employee=new ModelMapper().map(employeeDTO,Employee.class);
        System.out.println("post "+employee);
        return employeeDao.save(employee);


    }

    @Override
    public ObservableList<Employee> getAllEmployees() {
        return employeeDao.getAll();

    }

    @Override
    public ObservableList<String> getAllEmployeeIds() {
        return employeeDao.getAllEmployeeIds();
    }
}
