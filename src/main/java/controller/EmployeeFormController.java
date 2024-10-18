package controller;

import dto.EmployeeDTO;
import entity.Employee;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    public TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadTable();

    }

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {

        String name=txtName.getText();
        String company=txtCompany.getText();
        String eMail=txtEmail.getText();

        EmployeeDTO employeeDTO=new EmployeeDTO(
                name,company,eMail
        );

        EmployeeService employeeService=ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

        if (employeeService.addEmployee(employeeDTO)){
            new Alert(Alert.AlertType.INFORMATION,"Employee Add Successfully..!").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Employee Not Add..!").show();
        }



    }

    @FXML
    void btnDeleteEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateEmployeeOnAction(ActionEvent event) {

    }

    public void loadTable(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        EmployeeService employeeService=ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        ObservableList<Employee> employeeObservableList=employeeService.getAllEmployees();
        System.out.println(employeeObservableList);
        tblEmployee.setItems(employeeObservableList);
    }



}
