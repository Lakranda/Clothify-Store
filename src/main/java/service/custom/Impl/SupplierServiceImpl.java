package service.custom.Impl;

import entity.Product;
import entity.Supplier;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.custom.ProductDao;
import repository.custom.SupplierDao;
import service.SuperService;
import service.custom.SupplierService;
import util.DaoType;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {

    SupplierDao supplierDao=DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);


    @Override
    public Supplier getSelectedSupplier(String supplierId) {

        return supplierDao.getSelectedSupplier(supplierId);

    }

    @Override
    public boolean addSupplierWithProducts(Supplier supplier, List<Product> products) {
        //System.out.println(products);


        products.forEach(product -> {
            supplier.addProduct(product);
            System.out.println("pre product is - "+product);
        });

        return supplierDao.save(supplier);

    }

    @Override
    public ObservableList<Supplier> getAllSupplier() {

        return supplierDao.getAll();

    }

    @Override
    public ObservableList<String> getAllSupplierIds() {
        return supplierDao.getAllSupplierIds();
    }

    @Override
    public boolean deleteSupplier(String id) {
        return supplierDao.delete(id);
    }
}
