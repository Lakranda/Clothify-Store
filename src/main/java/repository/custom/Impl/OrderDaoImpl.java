package repository.custom.Impl;

import entity.Employee;
import entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.OrderDao;
import util.HibernateUtil;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private Session session = HibernateUtil.getSession(); // Directly use Hibernate session

    @Override
    public boolean save(Order order) {
        System.out.println(order);
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(order);  // Using Hibernate's save method
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of error
            }
            System.out.println("Order save Hibernate error - " + e);
        } finally {
            session.close(); // Ensure session is closed after operation
        }
        return false;
    }

    @Override
    public ObservableList<Order> getAll() {

        ObservableList<Order> orderList = FXCollections.observableArrayList();

        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            // Use HQL or Criteria to fetch all CustomerEntities
            List<Order> orders = session.createQuery("FROM Order", Order.class).list();

            orderList.addAll(orders);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Hibernate error " + e);
        }

        return orderList;

    }


}
