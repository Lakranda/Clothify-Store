package entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate date;
    private String time;
    private String paymentMethod;
    private Double total;
    private Long empId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderItem> orderItems=new ArrayList<>();

    public Order() {
    }

    public Order(LocalDate date, String time, String paymentMethod, Double total, Long empId) {
        this.date = date;
        this.time = time;
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.empId = empId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this); // Ensure the order is set in OrderItem
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.setOrder(null); // Nullify the reference
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", total=" + total +
                ", empId='" + empId + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }


}
