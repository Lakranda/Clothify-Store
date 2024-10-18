package controller;

import dto.UserTypeDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.UserService;
import util.ServiceType;

import java.io.IOException;

public class LoginFormController {

    public Hyperlink forgotPw;
    public Label errorMsg;
    @FXML
    private TextField eMail;

    @FXML
    private PasswordField password;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

        String _email=eMail.getText();
        String _password=password.getText();

        UserService service=ServiceFactory.getInstance().getServiceType(ServiceType.USER);
        UserTypeDTO userType=service.getUserType(_email,_password);
        //System.out.println("User Type is "+userType);
        Stage currentStage = (Stage) eMail.getScene().getWindow();

        if (userType!=null){
            switch (userType.getUserType()){
                case "Admin" :

                                System.out.println("A");

                                Stage stageAdmin=new Stage();
                                try {
                                    stageAdmin.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/admin_dashboard_form.fxml"))));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                stageAdmin.show();

                                //want to close now stage
                                currentStage.close();

                                break;

                case "Employee" :

                                System.out.println("E");

                                try {
                                    FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/user_dashboard_form.fxml")) ;
                                    Parent root = loader.load();
                                    UserDashboardFormController user=loader.getController();
                                    user.setEmployeeId(userType.getEmpId());
                                    Stage stageEmployee=new Stage();
                                    stageEmployee.setScene(new Scene(root));
                                    stageEmployee.show();

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                //want to close now stage
                                currentStage.close();

                                break;
            }
        }else {
            System.out.println("N");

            forgotPw.setText("forgot password ?");
            errorMsg.setText("invalid username or password");

        }




    }

    @FXML
    void forgotPassword(ActionEvent event) {

    }

}
