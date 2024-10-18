package service.custom.Impl;

import entity.Order;
import entity.OrderItem;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import repository.custom.OrderDao;
import service.custom.OrderService;
import util.DaoType;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean placeNewOrder(Order order, List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }

        OrderDao orderDao= DaoFactory.getInstance().getDaoType(DaoType.ORDER);
        return orderDao.save(order);    }

    @Override
    public ObservableList<Order> getAllOrders() {

        OrderDao orderDao=DaoFactory.getInstance().getDaoType(DaoType.ORDER);
        return orderDao.getAll();

    }
}
