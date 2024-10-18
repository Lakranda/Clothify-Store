package service.custom.Impl;

import entity.Product;
import entity.Supplier;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.custom.OrderDao;
import repository.custom.ProductDao;
import service.custom.ProductService;
import util.DaoType;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao= DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);



    @Override
    public boolean addProductWithSuppliers(Product product, List<Supplier> suppliers) {
        System.out.println(suppliers);
        suppliers.forEach(supplier -> {
            product.addSupplier(supplier);
        });

        return productDao.save(product);

    }

    @Override
    public ObservableList<Product> getAllProduct() {

        return productDao.getAll();

    }

    @Override
    public ObservableList<String> getAllProductIds() {
        return productDao.getAllProductIds();
    }

    @Override
    public Product getSelectedProduct(String productId) {

        return productDao.getSelectedProduct(productId);

    }

    @Override
    public void changeProductQty(Long productId,Integer qty) {
        productDao.changeProductQty(productId,qty);
    }


}
