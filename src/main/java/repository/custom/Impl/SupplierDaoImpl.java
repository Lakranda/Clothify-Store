package repository.custom.Impl;

import entity.Employee;
import entity.Product;
import entity.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.custom.SupplierDao;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(Supplier supplier) {
        System.out.println(supplier);

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(supplier);
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
    public ObservableList<Supplier> getAll() {

        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            // Use HQL or Criteria to fetch all CustomerEntities
            List<Supplier> suppliers = session.createQuery("FROM Supplier", Supplier.class).list();

            supplierList.addAll(suppliers);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Hibernate error " + e);
        }

        return supplierList;

    }

    @Override
    public ObservableList<String> getAllSupplierIds() {

        ObservableList<String> supplierIdsObservableArray=FXCollections.observableArrayList();
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Long> supplierIds = null;

        try {
            // Start a transaction
            transaction = session.beginTransaction();

            // Create an HQL query to retrieve only the employee ids
            Query<Long> query = session.createQuery("SELECT s.id FROM Supplier s", Long.class);

            // Execute the query and retrieve the result list
            supplierIds = query.getResultList();

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

        supplierIds.forEach(id->{
            supplierIdsObservableArray.add(id.toString());
        });

        return supplierIdsObservableArray;

    }

    @Override
    public Supplier getSelectedSupplier(String supplierId) {


        Transaction transaction = null;
        Supplier supplier = null;

        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            // Fetch the product by its ID
            supplier = session.get(Supplier.class, Long.parseLong(supplierId));

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


        return new Supplier(
                supplier.getSupplierId(),supplier.getName(),supplier.getCompany(),supplier.getEmail()
        );


    }

    //TODO delete method error fix

    @Override
    public boolean delete(String id) {

        Transaction transaction = null;
        Session session = null;

        try {
            // Initialize the session
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            // Find the supplier by supplierId
            Supplier supplier = session.get(Supplier.class, Long.parseLong(id));

            // If supplier exists, delete associated supplier products first
            if (supplier != null) {
                // HQL to delete associated products in supplier_product
                String hqlDelete = "DELETE FROM Supplier_Product sp WHERE sp.supplier.supplierId = :supplierId";
                Query query = session.createQuery(hqlDelete);
                query.setParameter("supplierId", id);
                query.executeUpdate(); // Delete associated supplier products

                // Now delete the supplier
                session.delete(supplier);
                transaction.commit(); // Commit the transaction
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close(); // Ensure the session is closed
            }
        }

    }
}
