package service.custom;

import entity.Employee;
import entity.Product;
import entity.Supplier;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {
    Supplier getSelectedSupplier(String supplierId);

    boolean addSupplierWithProducts(Supplier supplier, List<Product> products);
    ObservableList<Supplier> getAllSupplier();
    ObservableList<String> getAllSupplierIds();
    boolean deleteSupplier(String id);


}
