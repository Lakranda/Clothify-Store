package repository.custom;

import entity.Supplier;
import javafx.collections.ObservableList;
import repository.CrudDao;

public interface SupplierDao extends CrudDao<Supplier> {
    ObservableList<String> getAllSupplierIds();
    Supplier getSelectedSupplier(String supplierId);

}
