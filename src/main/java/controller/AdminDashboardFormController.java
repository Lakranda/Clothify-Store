package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardFormController {

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) {

        Stage employeeStage=new Stage();
        try {
            employeeStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/employee_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        employeeStage.show();

    }

    @FXML
    void btnAddUserOnAction(ActionEvent event) {

        Stage userStage=new Stage();
        try {
            userStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userStage.show();

    }

    public void btnSuplierOnAction(ActionEvent actionEvent) {

        Stage supplierStage=new Stage();
        try {
            supplierStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/supplier_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        supplierStage.show();
    }

    public void btnProductOnAction(ActionEvent actionEvent) {

        Stage productStage=new Stage();
        try {
            productStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/product_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productStage.show();

    }

    public void btnReportsOnAction(ActionEvent actionEvent) {
    }


}
