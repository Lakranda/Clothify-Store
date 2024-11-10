package controller;

import dto.CartDTO;
import dto.ReturnsDTO;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import service.ServiceFactory;
import service.custom.OrderService;
import service.custom.ProductService;
import service.custom.ReturnService;
import util.ServiceType;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ReturnOrderFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbProductId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private ImageView image;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblProductName;

    @FXML
    private TableView<ReturnsDTO> tblCart;

    @FXML
    private TextField txtQty;

    private Double netTotal=0.0;

    ObservableList<ReturnsDTO> returnsDTOS= FXCollections.observableArrayList();
    ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));


        cmbProductId.getSelectionModel().selectedItemProperty().addListener(((observableValue, s, newValue) ->{
            if(newValue!=null){
                searchProduct(newValue);
            }
        } ));

        loadProductIds();


    }

    private void searchProduct(String prosuctId) {

        ProductService productService= ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        Product selectedProduct = productService.getSelectedProduct(prosuctId);

        lblProductName.setText(selectedProduct.getName());
        lblPrice.setText(selectedProduct.getPrice().toString());


    }

    public void loadProductIds(){

        ObservableList<String> productIds=productService.getAllProductIds();

        cmbProductId.setItems(productIds);

    }


    @FXML
    void btnAddToReturnsOnAction(ActionEvent event) {

        Long productId= Long.parseLong(cmbProductId.getValue());
        String name=lblProductName.getText();
        Double unitPrice=Double.parseDouble(lblPrice.getText());
        int qty=Integer.parseInt(txtQty.getText());
        Double total=qty*unitPrice;

        returnsDTOS.add(
                new ReturnsDTO(productId,name,unitPrice,qty,total)
        );

        calcNetTotal(total);
        //calcNetTotal(23000.0);

        tblCart.setItems(returnsDTOS);

        txtQty.setText("");

        productService.changeProductQty(productId,qty);

    }

    private void calcNetTotal(Double total) {
        netTotal+=total;
        lblNetTotal.setText(netTotal.toString());

    }

    @FXML
    void btnReturnOrderOnAction(ActionEvent event) {




        ObservableList<ReturnItem> returnItems = FXCollections.observableArrayList();

        returnsDTOS.forEach(cartItem->
                returnItems.add(new ReturnItem(cartItem.getId(),cartItem.getQty(),cartItem.getTotal()))

        );

        Returns returns=new Returns(
                LocalDate.now(), LocalTime.now().toString(),netTotal
        );

        ReturnService returnService= ServiceFactory.getInstance().getServiceType(ServiceType.RETURN);


        if (returnService.returnOrder(returns,returnItems)){
            tblCart.setItems(FXCollections.observableArrayList());

            new Alert(Alert.AlertType.INFORMATION,"Return Place Successfully..!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Return is not Placed..!").show();
        }





    }

}
