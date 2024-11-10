package repository.custom.Impl;

import entity.Returns;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.ReturnDao;
import util.HibernateUtil;

public class ReturnDaoImpl implements ReturnDao {

    private Session session = HibernateUtil.getSession(); // Directly use Hibernate session

    @Override
    public boolean save(Returns returns) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(returns);  // Using Hibernate's save method
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of error
            }
            System.out.println("Returns save Hibernate error - " + e);
        } finally {
            session.close(); // Ensure session is closed after operation
        }
        return false;

    }

    @Override
    public ObservableList<Returns> getAll() {
        return null;
    }
}
