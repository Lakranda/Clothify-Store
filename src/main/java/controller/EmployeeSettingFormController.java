package controller;

import entity.Employee;
import entity.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.ProductService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeSettingFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbEmpId;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    EmployeeService employeeService=ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadEmployeeIds();

        cmbEmpId.getSelectionModel().selectedItemProperty().addListener(((observableValue, s, newValue) ->{
            if(newValue!=null){
                searchEmployee(newValue);
            }
        } ));

    }

    private void loadEmployeeIds() {

        ObservableList<String> employeeIds = employeeService.getAllEmployeeIds();

        cmbEmpId.setItems(employeeIds);

    }

    private void searchEmployee(String id) {

        Employee employee = employeeService.getSelectedEmployee(id);

        txtName.setText(employee.getName());
        txtEmail.setText(employee.getEmail());
        txtCompany.setText(employee.getCompany());

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeteleUserOnAction(ActionEvent event) {

        boolean isDelete=employeeService.deleteUser(cmbEmpId.getValue());

        if(isDelete){
            new Alert(Alert.AlertType.INFORMATION,"Employee is deleted..!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error , Employee Not deleted..!").show();
        }

    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) {

    }


}
