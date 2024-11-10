package service.custom;

import dto.UserDTO;
import dto.UserTypeDTO;
import entity.Employee;
import entity.User;
import javafx.collections.ObservableList;
import service.SuperService;

public interface UserService extends SuperService {
    UserTypeDTO getUserType(String email, String password);
    boolean setNewUser(UserDTO userDTO);
    ObservableList<User> getAllUsers();
    boolean changePassword(String email,String current,String nEw);

    ObservableList<String> getUserEmails();

    User getSelectedUser(String email);

    boolean deleteUser(String value);
}
