package controller;

import entity.Product;
import entity.Supplier;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import service.ServiceFactory;
import service.custom.ProductService;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductSettingFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbProducts;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private ImageView productImage;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSize;

    ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadProductIds();

        cmbProducts.getSelectionModel().selectedItemProperty().addListener(((observableValue, s, newValue) ->{
            if(newValue!=null){
                searchProduct(newValue);
            }
        } ));

    }

    private void searchProduct(String id) {

        Product selectedProduct = productService.getSelectedProduct(id);

        cmbType.setValue(selectedProduct.getType());
        txtName.setText(selectedProduct.getName());
        txtPrice.setText(selectedProduct.getPrice().toString());
        txtQty.setText(selectedProduct.getQty().toString());
        txtSize.setText(selectedProduct.getSize());
        System.out.println("selected product size is"+selectedProduct.getSize());

    }

    private void loadProductIds() {

        ObservableList<String> productIds = productService.getAllProductIds();

        cmbProducts.setItems(productIds);

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteProductOnAction(ActionEvent event) {

        boolean isDelete=productService.deleteProduct(cmbProducts.getValue());

        if(isDelete){
            new Alert(Alert.AlertType.INFORMATION,"Product is deleted..!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error , Product Not deleted..!").show();
        }

    }

    @FXML
    void btnUpdateProductOnAction(ActionEvent event) {

    }


}
