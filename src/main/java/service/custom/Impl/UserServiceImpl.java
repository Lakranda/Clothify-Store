package service.custom.Impl;

import dto.UserDTO;
import dto.UserTypeDTO;
import entity.User;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import repository.custom.UserDao;
import service.custom.UserService;
import util.DaoType;

public class UserServiceImpl implements UserService {

    UserDao userDao=DaoFactory.getInstance().getDaoType(DaoType.USER);


    @Override
    public UserTypeDTO getUserType(String email, String password) {

        return userDao.getUserType(email,password);

    }

    @Override
    public boolean setNewUser(UserDTO userDTO) {

        User user=new ModelMapper().map(userDTO,User.class);
        return userDao.save(user);

    }

    @Override
    public ObservableList<User> getAllUsers() {

        return userDao.getAll();

    }

    @Override
    public boolean changePassword(String email,String current, String nEw) {

        return userDao.changePassword(email,current,nEw);

    }

    @Override
    public ObservableList<String> getUserEmails() {

        return userDao.getUserEmails();

    }

    @Override
    public User getSelectedUser(String email) {

        return userDao.getSelectedUser(email);

    }

    @Override
    public boolean deleteUser(String email) {

        return userDao.delete(email);

    }
}
