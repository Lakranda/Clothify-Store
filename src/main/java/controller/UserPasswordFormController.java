package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import service.ServiceFactory;
import service.custom.UserService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPasswordFormController implements Initializable {

    public Label isRightPw;
    public Button btnChangePw;
    @FXML
    private TextField currentPw;

    @FXML
    private TextField newPw;

    private String email="employee9@gmail.com";
    private String correctNewPw;

    @FXML
    void btnChangePwOnAction(ActionEvent event) {

        UserService userService= ServiceFactory.getInstance().getServiceType(ServiceType.USER);
        if (userService.changePassword(email,currentPw.getText(),correctNewPw)){
            new Alert(Alert.AlertType.INFORMATION,"Password was changed..!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"incorrect password, Try again..!").show();
        }

    }

    public void isLegalPasswordOnaction(KeyEvent keyEvent) {
        String newPassword = newPw.getText();
        System.out.println(newPassword);
        // Check if the password meets the requirements (8 characters, includes letters, numbers, and symbols)
        if (isValidPassword(newPassword)) {
            // If valid, set the label text to "right" and color to green
            isRightPw.setText("Correct");
            isRightPw.setStyle("-fx-text-fill: green;");
            correctNewPw=newPassword;
            btnChangePw.setDisable(false);
        } else {
            // If invalid, set the label text to "wrong" and color to red
            isRightPw.setText("8 characters,A,@,1");
            isRightPw.setStyle("-fx-text-fill: red;");
            correctNewPw=null;
            btnChangePw.setDisable(true);

        }
    }

    // Helper method to validate the password
    private boolean isValidPassword(String password) {
        // Regular expression to check password has at least 8 characters, letters, numbers, and symbols
        String passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password != null && password.matches(passwordPattern);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnChangePw.setDisable(true);
    }
}
