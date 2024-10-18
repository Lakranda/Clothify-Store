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


    @Override
    public UserTypeDTO getUserType(String email, String password) {

        UserDao userDao=DaoFactory.getInstance().getDaoType(DaoType.USER);
        return userDao.getUserType(email,password);

    }

    @Override
    public boolean setNewUser(UserDTO userDTO) {

        User user=new ModelMapper().map(userDTO,User.class);
        UserDao userDao=DaoFactory.getInstance().getDaoType(DaoType.USER);
        return userDao.save(user);

    }

    @Override
    public ObservableList<User> getAllUsers() {

        UserDao userDao=DaoFactory.getInstance().getDaoType(DaoType.USER);
        return userDao.getAll();

    }
}
