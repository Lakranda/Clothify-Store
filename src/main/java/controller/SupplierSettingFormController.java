package controller;

import entity.Supplier;
import entity.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import service.ServiceFactory;
import service.custom.SupplierService;
import service.custom.UserService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierSettingFormController implements Initializable {

    public ComboBox<String> cmbSupplierId;


    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    SupplierService supplierService=ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadSupplierIds();

        cmbSupplierId.getSelectionModel().selectedItemProperty().addListener(((observableValue, s, newValue) ->{
            if(newValue!=null){
                searchSupplier(newValue);
            }
        } ));

    }

    private void loadSupplierIds() {

        ObservableList<String> supplierIds = supplierService.getAllSupplierIds();

        cmbSupplierId.setItems(supplierIds);

    }

    private void searchSupplier(String id) {

        Supplier selectedSupplier = supplierService.getSelectedSupplier(id);

        txtName.setText(selectedSupplier.getName());
        txtEmail.setText(selectedSupplier.getEmail());
        txtCompany.setText(selectedSupplier.getCompany());

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeteleUserOnAction(ActionEvent event) {

        boolean isDelete=supplierService.deleteSupplier(cmbSupplierId.getValue());

        if(isDelete){
            new Alert(Alert.AlertType.INFORMATION,"Supplier is deleted..!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error , Supplier Not deleted..!").show();
        }

    }

    @FXML
    void btnUpdateUserOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSupplierId(ActionEvent event) {

    }


}
