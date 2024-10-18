package controller;

import dto.UserDTO;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.UserService;
import util.ServiceType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    public TableView<User> tblUser;
    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private ComboBox<String> cmbUserType;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colUserType;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    UserService userService=ServiceFactory.getInstance().getServiceType(ServiceType.USER);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> userTypeList= FXCollections.observableArrayList();
        userTypeList.add("Admin");
        userTypeList.add("Employee");

        cmbUserType.setItems(userTypeList);

        loadEmployeeIds();
        loadTable();

    }

    public void loadTable(){

        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        //colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colUserType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));

        ObservableList<User> userObservableList=userService.getAllUsers();
        tblUser.setItems(userObservableList);

    }

    public void loadEmployeeIds(){

        EmployeeService employeeService=ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        ObservableList<String> employeeIds = employeeService.getAllEmployeeIds();

        cmbEmployeeId.setItems(employeeIds);

    }

    @FXML
    void btnDeleteUserOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) {

    }

    @FXML
    void btnUserAddOnAction(ActionEvent event) {

        UserDTO userDTO=new UserDTO(
                txtEmail.getText(),
                txtPassword.getText(),
                cmbUserType.getValue(),
                cmbEmployeeId.getValue()
        );


        if (userService.setNewUser(userDTO)){
            new Alert(Alert.AlertType.INFORMATION,"New User Add Successfully..!").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"User Not Add..!").show();
        }

    }


}
