package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;
    private String name;
    private String company;
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "supplier_product",
            joinColumns = @JoinColumn(name = "supplierId"),
            inverseJoinColumns = @JoinColumn(name = "productId")
    )
    private List<Product> supplierProducts=new ArrayList<>();

    public Supplier() {
    }

    public Supplier(String name, String company, String eMail) {
        this.name = name;
        this.company = company;
        this.email = eMail;
    }

    public Supplier(Long supplierId, String name, String company, String email) {
        this.supplierId = supplierId;
        this.name = name;
        this.company = company;
        this.email = email;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getSupplierProducts() {
        return supplierProducts;
    }

    public void setSupplierProducts(List<Product> supplierProducts) {
        this.supplierProducts = supplierProducts;
    }

    public void addProduct(Product product) {
        this.supplierProducts.add(product);
        product.getProductSuppliers().add(this);
    }

    public void removeProduct(Product product) {
        this.supplierProducts.remove(product);
        product.getProductSuppliers().remove(this);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", eMail='" + email + '\'' +
                //", supplierProducts=" + supplierProducts +
                '}';
    }
}
