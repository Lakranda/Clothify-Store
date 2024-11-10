package dto;

public class ReturnsDTO {
    private Long id;
    private String name;
    private Double price;
    private int qty;
    private Double total;

    public ReturnsDTO() {
    }

    public ReturnsDTO(Long id, String name, Double price, int qty, Double total) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
