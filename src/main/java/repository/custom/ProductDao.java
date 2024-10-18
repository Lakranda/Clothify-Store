package repository.custom;

import entity.Product;
import javafx.collections.ObservableList;
import repository.CrudDao;

public interface ProductDao extends CrudDao<Product> {
    ObservableList<String> getAllProductIds();
    Product getSelectedProduct(String productId);
    void changeProductQty(Long productId,Integer qty);
}
