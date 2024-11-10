package controller;

import entity.Product;
import entity.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.ProductService;
import service.custom.SupplierService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductFormController implements Initializable {

    @FXML
    private Button addProductSuppliers;

    @FXML
    private ComboBox<String> cmbSuppliers;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private ImageView productImage;

    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private TableView<Supplier> tblProductSuppliers;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSize;

    SupplierService supplierService=ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    ProductService productService= ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    ObservableList<String> productTypes= FXCollections.observableArrayList();

    List<Supplier> ProductSuppliers=new ArrayList<>();
    ObservableList<Supplier> tblProductSuppliersView= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadSupplierIds();

        loadTable();

    }

    public void loadSupplierIds(){

        ObservableList<String> supplierIds=supplierService.getAllSupplierIds();

        cmbSuppliers.setItems(supplierIds);

    }

    public void loadTable(){

        colId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        productTypes.add("KIDS");
        productTypes.add("GENTS");
        productTypes.add("LADIES");
        cmbType.setItems(productTypes);

        ObservableList<Product> productObservableList=productService.getAllProduct();
        tblProduct.setItems(productObservableList);

    }

    @FXML
    void addNewProductForSupplier(ActionEvent event) {

        String supplierId=cmbSuppliers.getValue();
        Supplier supplier=supplierService.getSelectedSupplier(supplierId);
        System.out.println(supplier);

        ProductSuppliers.add(supplier);
        tblProductSuppliersView.add(supplier);

        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblProductSuppliers.setItems(tblProductSuppliersView);

    }

    public void btnAddNewProductOnAction(ActionEvent actionEvent) {

        Product product=new Product(
                txtName.getText(),cmbType.getValue(),txtSize.getText(),Double.parseDouble(txtPrice.getText()),Integer.parseInt(txtQty.getText())
        );

        // want to add supplier

        List<Supplier> suppliers=new ArrayList<>();

        suppliers.add(
                new Supplier("Ginadasa","Galawela products","ginadasa@gmail.com")
        );

        boolean isAdd=productService.addProductWithSuppliers(product,suppliers);

        if (isAdd){
            new Alert(Alert.AlertType.INFORMATION,"New Product Add Successfully..!").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"error . Product is Not Add").show();
        }


    }

    public void btnProductSettingOnAction(ActionEvent actionEvent) {


        try {

            Stage productsttingStage=new Stage();
            productsttingStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/product_setting_form.fxml"))));
            productsttingStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
