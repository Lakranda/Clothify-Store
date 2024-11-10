package repository.custom.Impl;

import dto.UserTypeDTO;
import entity.Employee;
import entity.Supplier;
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
    public boolean changePassword(String email, String currentPassword, String newPassword) {
        Transaction transaction = null;
        Session session = null;
        boolean isPasswordChanged = false;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            // HQL query to fetch the user by email and current password
            String hql = "FROM User WHERE email = :email AND password = :currentPassword";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", email);
            query.setParameter("currentPassword", currentPassword);

            // Fetch the result
            User user = query.uniqueResult();

            // Check if the user exists and the current password matches
            if (user != null) {
                // Update the user's password
                user.setPassword(newPassword);
                session.update(user);

                // Commit the transaction to persist the changes
                transaction.commit();
                isPasswordChanged = true;
            } else {
                System.out.println("Error: Current password does not match or user not found.");
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback if there is an error
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Ensure the session is closed
            }
        }

        return isPasswordChanged;
    }

    @Override
    public ObservableList<String> getUserEmails() {

        ObservableList<String> userEmailsObservableArray=FXCollections.observableArrayList();
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<String> userEmails = null;

        try {
            // Start a transaction
            transaction = session.beginTransaction();

            // Create an HQL query to retrieve only the employee ids
            Query<String> query = session.createQuery("SELECT u.email FROM User u", String.class);

            // Execute the query and retrieve the result list
            userEmails = query.getResultList();

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

        userEmails.forEach(email->{
            userEmailsObservableArray.add(email);
        });

        return userEmailsObservableArray;

    }

    @Override
    public User getSelectedUser(String email) {


        Transaction transaction = null;
        User user = null;

        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            // Fetch the product by its ID
            user = session.get(User.class, email);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


        return new User(
                user.getEmail(),user.getPassword(),user.getType(), user.getEmpId()
        );

    }

    @Override
    public boolean delete(String email) {

        Transaction transaction = null;
        Session session = null;

        try {
            // Initialize the session
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            // Use HQL to fetch the user by email
            String hql = "FROM User u WHERE u.email = :email";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", email);

            User user = query.uniqueResult();

            // If user exists, delete it
            if (user != null) {
                session.delete(user);
                transaction.commit(); // Commit the transaction
                System.out.println("User deleted successfully");
                return true;
            } else {
                System.out.println("User not found with email: " + email);
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
