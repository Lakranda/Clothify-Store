package repository.custom.Impl;

import dto.UserTypeDTO;
import entity.Employee;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.custom.UserDao;
import util.HibernateUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {

    public UserTypeDTO getUserType(String _email, String _password) {
        Transaction transaction = null;
        UserTypeDTO userTypeDTO = null;
        Session session = null;

        try {
            // Initialize the session
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            // HQL Query to fetch user type and empId based on email and password
            String hql = "SELECT u.type, u.empId FROM User u WHERE u.email = :email AND u.password = :password";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            query.setParameter("email", _email);
            query.setParameter("password", _password);

            List<Object[]> result = query.list();

            if (!result.isEmpty()) {
                String userType = (String) result.get(0)[0];  // User type (Admin/Employee)
                Long empId = null;  // Initialize empId as null

                if (result.get(0)[1] != null) {
                    empId = Long.parseLong(result.get(0)[1].toString());  // Convert String to Long
                }

                // Create a new UserTypeDTO to return both values
                userTypeDTO = new UserTypeDTO(userType, empId);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback if transaction is active
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Ensure session is closed
            }
        }

        return userTypeDTO;
    }



    @Override
    public boolean save(User user) {
        try {
            Session session= HibernateUtil.getSession();
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            session.close();
            return true;

        }catch (Exception e){
            System.out.println("save Hibernate error "+e);
            return false;
        }
    }

    @Override
    public ObservableList<User> getAll() {

        ObservableList<User> userList = FXCollections.observableArrayList();

        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            // Use HQL or Criteria to fetch all CustomerEntities
            List<User> users = session.createQuery("FROM User", User.class).list();

            userList.addAll(users);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Hibernate error " + e);
        }

        return userList;

    }
}
