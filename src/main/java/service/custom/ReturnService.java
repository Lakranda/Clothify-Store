package service.custom;

import entity.Order;
import entity.OrderItem;
import entity.ReturnItem;
import entity.Returns;
import service.SuperService;

import java.util.List;

public interface ReturnService extends SuperService {
    boolean returnOrder(Returns returns, List<ReturnItem> returnItems);
}
