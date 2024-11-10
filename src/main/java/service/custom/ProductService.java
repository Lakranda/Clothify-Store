package service.custom;

import entity.Employee;
import entity.Product;
import entity.Supplier;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
    boolean addProductWithSuppliers(Product product, List<Supplier> suppliers);
    ObservableList<Product> getAllProduct();
    ObservableList<String> getAllProductIds();
    Product getSelectedProduct(String productId);
    void changeProductQty(Long productId,Integer qty);

    boolean deleteProduct(String id);
}
