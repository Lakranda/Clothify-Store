package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String name;
    private String type;
    private String size;
    private Double price;
    private Integer qty;
    //private Object image;

    @ManyToMany(mappedBy = "supplierProducts")
    private List<Supplier> productSuppliers=new ArrayList<>();

    public Product() {
    }

    public Product(String name, String type, String size, Double price, Integer qty) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
        this.qty = qty;
    }

    public Product(Long productId, String name, String type, String size, Double price, Integer qty) {
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
        this.qty = qty;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public List<Supplier> getProductSuppliers() {
        return productSuppliers;
    }

    public void setProductSuppliers(List<Supplier> productSuppliers) {
        this.productSuppliers = productSuppliers;
    }

    public void addSupplier(Supplier supplier) {
        System.out.println(supplier);
        this.productSuppliers.add(supplier);
        supplier.getSupplierProducts().add(this);
    }

    public void removeSupplier(Supplier supplier) {
        this.productSuppliers.remove(supplier);
        supplier.getSupplierProducts().remove(this);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                //", productSuppliers=" + productSuppliers +
                '}';
    }
}
