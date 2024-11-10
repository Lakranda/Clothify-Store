package entity;


import jakarta.persistence.*;

@Entity
@Table(name = "return_items")
public class ReturnItem {

    @Id
    private Long productId;
    private Integer qty;
    private Double total;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "returnId", nullable = false)
    private Returns returns;

    public ReturnItem() {
    }

    public ReturnItem(Long productId, Integer qty, Double total) {
        this.productId = productId;
        this.qty = qty;
        this.total = total;


    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Returns getOrder() {
        return returns;
    }

    public void setOrder(Returns returns) {
        this.returns = returns;
    }

    @Override
    public String toString() {
        return "ReturnItem{" +
                "productId=" + productId +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
