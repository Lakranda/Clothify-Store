package controller;

import entity.Product;
import entity.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import service.ServiceFactory;
import service.custom.ProductService;
import service.custom.UserService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class UserSettingFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbEmail;

    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private ComboBox<String> cmbUserType;

    @FXML
    private TextField txtPassword;

    UserService userService= ServiceFactory.getInstance().getServiceType(ServiceType.USER);



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadUserEmails();

        cmbEmail.getSelectionModel().selectedItemProperty().addListener(((observableValue, s, newValue) ->{
            if(newValue!=null){
                searchUser(newValue);
            }
        } ));


    }

    private void loadUserEmails() {

        ObservableList<String> userIds = userService.getUserEmails();

        cmbEmail.setItems(userIds);

    }

    private void searchUser(String email) {

        User selectedUser = userService.getSelectedUser(email);

        cmbEmployeeId.setValue(selectedUser.getEmpId());
        txtPassword.setText(selectedUser.getPassword());
        cmbUserType.setValue(selectedUser.getType());

    }


    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeteleUserOnAction(ActionEvent event) {

        boolean isDelete=userService.deleteUser(cmbEmail.getValue());

        if(isDelete){
            new Alert(Alert.AlertType.INFORMATION,"User is deleted..!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error , User Not deleted..!").show();
        }


    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) {

    }

}
