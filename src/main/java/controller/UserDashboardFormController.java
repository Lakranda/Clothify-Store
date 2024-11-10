package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashboardFormController {

    private Long empId;

    public void btnReportsOnAction(ActionEvent actionEvent) {
    }

    public void btnOrderOnAction(ActionEvent actionEvent) {

        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/place_order_form.fxml")) ;
            Parent root = loader.load();
            PlaceOrderFormController orderForm=loader.getController();
            orderForm.setEmployeeId(empId);
            Stage stageEmployee=new Stage();
            stageEmployee.setScene(new Scene(root));
            stageEmployee.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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

    public void setEmployeeId(long employeeId) {

        empId=employeeId;

    }

    public void btnUserSettingOnAction(ActionEvent actionEvent) {

        try {
            Stage settingStage=new Stage();
            settingStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user_password_form.fxml"))));
            settingStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
