package controller;

import dto.CartDTO;


import entity.Order;
import entity.OrderItem;
import entity.Product;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import service.ServiceFactory;
import service.custom.OrderService;
import service.custom.ProductService;
import util.ServiceType;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {

    public TableView tblCart;

    public TextField txtQty;

    @FXML
    private ComboBox<String> cmbMethod;

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
    private Label lblDate;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblProductName;

    @FXML
    private Label lblTime;

    @FXML
    private Spinner<String> spinQuntity;

    private Double netTotal=0.0;

    private String orderId="Default Oid";

    private Long empId;

    private OrderService orderService=ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);

    ObservableList<CartDTO> cartDTOS= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> paymentMethods=FXCollections.observableArrayList();
        paymentMethods.add("Cash");
        paymentMethods.add("Card");
        cmbMethod.setItems(paymentMethods);

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

        loadDateAndTime();

        loadProductIds();

    }

    private void searchProduct(String prosuctId) {

        ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        Product selectedProduct = productService.getSelectedProduct(prosuctId);

        lblProductName.setText(selectedProduct.getName());
        lblPrice.setText(selectedProduct.getPrice().toString());


    }

    public void loadProductIds(){

        ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        ObservableList<String> productIds=productService.getAllProductIds();

        cmbProductId.setItems(productIds);

    }


    private void loadDateAndTime() {
        //
        String dateNow=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        lblDate.setText(dateNow);
        //
        //
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

        Long productId= Long.parseLong(cmbProductId.getValue());
        String name=lblProductName.getText();
        Double unitPrice=Double.parseDouble(lblPrice.getText());
        int qty=Integer.parseInt(txtQty.getText());
        Double total=qty*unitPrice;

        cartDTOS.add(
                new CartDTO(productId,name,unitPrice,qty,total)
                //new CartDTO("P001","white rice",2300.0,10,23000.0)
        );

        calcNetTotal(total);
        //calcNetTotal(23000.0);

        tblCart.setItems(cartDTOS);

        txtQty.setText("");

        ProductService productService=ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
        productService.changeProductQty(productId,-qty);

    }

    private void calcNetTotal(Double total) {
        netTotal+=total;
        lblNetTotal.setText(netTotal.toString());

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {


        ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();

        cartDTOS.forEach(cartItem->
                orderItems.add(new OrderItem(cartItem.getId(),cartItem.getQty(),cartItem.getTotal()))

        );

        Order order=new Order(
                LocalDate.now(),lblTime.getText(),cmbMethod.getValue(),netTotal,empId
        );

        OrderService orderService= ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);


        if (orderService.placeNewOrder(order,orderItems)){
            tblCart.setItems(FXCollections.observableArrayList());

            new Alert(Alert.AlertType.INFORMATION,"Order Place Successfully..!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Order is not Placed..!").show();
        }



    }


    public void setEmployeeId(Long empId) {

        this.empId=empId;
        System.out.println("Employee id : "+empId);

    }
}
