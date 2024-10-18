package repository.custom.Impl;

import entity.Employee;
import entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.custom.ProductDao;
import util.HibernateUtil;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(Product product) {
        System.out.println(product);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ObservableList<Product> getAll() {


        ObservableList<Product> productList = FXCollections.observableArrayList();

        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            // Use HQL or Criteria to fetch all CustomerEntities
            List<Product> products = session.createQuery("FROM Product", Product.class).list();

            productList.addAll(products);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Hibernate error " + e);
        }

        return productList;

    }

    @Override
    public ObservableList<String> getAllProductIds() {

        ObservableList<String> productIdsObservableArray=FXCollections.observableArrayList();
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Long> productIds = null;

        try {
            // Start a transaction
            transaction = session.beginTransaction();

            // Create an HQL query to retrieve only the employee ids
            Query<Long> query = session.createQuery("SELECT p.id FROM Product p", Long.class);

            // Execute the query and retrieve the result list
            productIds = query.getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        productIds.forEach(id->{
            productIdsObservableArray.add(id.toString());
        });

        return productIdsObservableArray;

    }

    @Override
    public Product getSelectedProduct(String productId) {

        Transaction transaction = null;
        Product product = null;

        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            // Fetch the product by its ID
            product = session.get(Product.class, Long.parseLong(productId));

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


        return new Product(
                product.getProductId(),product.getName(),product.getType(),product.getType(),product.getPrice(),product.getQty()
        );

    }

    @Override
    public void changeProductQty(Long productId, Integer qtyChange) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            // Fetch the product by productId
            Product product = session.get(Product.class, productId);

            if (product != null) {
                // Calculate the new quantity (current quantity + qtyChange)
                Integer newQty = product.getQty() + qtyChange;

                // Make sure newQty is not negative
                if (newQty < 0) {
                    System.out.println("Error: Quantity cannot be negative.");
                    return;
                }

                // Set the new quantity
                product.setQty(newQty);

                // Persist the change
                session.update(product);

                transaction.commit(); // Commit the transaction
            } else {
                System.out.println("Error: Product with ID " + productId + " not found.");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of error
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Close the session
            }
        }
    }

}
