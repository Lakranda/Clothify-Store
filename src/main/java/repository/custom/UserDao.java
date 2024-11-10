package repository.custom;

import dto.UserTypeDTO;
import entity.User;
import javafx.collections.ObservableList;
import repository.CrudDao;

public interface UserDao extends CrudDao<User> {
    UserTypeDTO getUserType(String email, String password);
    boolean changePassword(String email,String current,String nEw);

    ObservableList<String> getUserEmails();

    User getSelectedUser(String email);

    boolean delete(String email);


}
