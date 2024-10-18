package repository.custom.Impl;

import entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.custom.EmployeeDao;
import util.HibernateUtil;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public boolean save(Employee employee) {

        try {
            Session session= HibernateUtil.getSession();
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();
            session.close();
            return true;

        }catch (Exception e){
            System.out.println("save Hibernate error "+e);
            return false;
        }


    }

    @Override
    public ObservableList<Employee> getAll() {

        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();

            // Use HQL or Criteria to fetch all CustomerEntities
            List<Employee> emoloyees = session.createQuery("FROM Employee", Employee.class).list();

            employeeList.addAll(emoloyees);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Hibernate error " + e);
        }

        return employeeList;

    }


    @Override
    public ObservableList<String> getAllEmployeeIds() {

        ObservableList<String> employeeIdsObservableArray=FXCollections.observableArrayList();
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        List<Long> employeeIds = null;

        try {
            // Start a transaction
            transaction = session.beginTransaction();

            // Create an HQL query to retrieve only the employee ids
            Query<Long> query = session.createQuery("SELECT e.id FROM Employee e", Long.class);

            // Execute the query and retrieve the result list
            employeeIds = query.getResultList();

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

        employeeIds.forEach(id->{
            employeeIdsObservableArray.add(id.toString());
        });

        return employeeIdsObservableArray;

    }
}
