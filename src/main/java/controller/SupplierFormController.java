package controller;

import entity.Product;
import entity.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.ProductService;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    public TableView<Supplier> tblSupplier;
    @FXML
    private ComboBox<String> cmbProducts;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProduct;

    @FXML
    private TableView<Product> tblSupplierProducts;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    List<Product> supplierProducts=new ArrayList<>();
    ObservableList<Product> tblSupplierProductsView= FXCollections.observableArrayList();

    SupplierService supplierService= ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadProductIds();

        loadTable();

    }

    public void loadTable(){

        colId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        ObservableList<Supplier> supplierObservableList=supplierService.getAllSupplier();
        tblSupplier.setItems(supplierObservableList);

    }

    public void loadProductIds(){
        ObservableList<String> productIds=productService.getAllProductIds();

        cmbProducts.setItems(productIds);

    }

    @FXML
    void AddNewSupplier(ActionEvent event) {

        Supplier supplier=new Supplier(
                txtName.getText(),txtCompany.getText(),txtEmail.getText()
        );

//        List<Product> productsT=new ArrayList<>();
//        productsT.add(
//                new Product("short","kids","L",1000.0,1000)
//        );
//        productsT.add(
//                new Product("shirt","kids","L",800.0,1000)
//        );

        //supplierProducts
        boolean isAdd=supplierService.addSupplierWithProducts(supplier,supplierProducts);

        if (isAdd){
            new Alert(Alert.AlertType.INFORMATION,"New Supplier Add Successfully..!").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"error. Supplier Not Add").show();
        }


    }

    @FXML
    void addNewProductForSupplier(ActionEvent event) {


        String productId=cmbProducts.getValue();
        Product product=productService.getSelectedProduct(productId);
        System.out.println(product);

        supplierProducts.add(product);
        tblSupplierProductsView.add(product);

        colProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblSupplierProducts.setItems(tblSupplierProductsView);




    }

}
