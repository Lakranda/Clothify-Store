package service.custom;

import entity.Employee;
import entity.Order;
import entity.OrderItem;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {
    boolean placeNewOrder(Order order, List<OrderItem> orderItems);
    ObservableList<Order> getAllOrders();

}
