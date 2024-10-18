package repository.custom;

import dto.UserTypeDTO;
import entity.User;
import repository.CrudDao;

public interface UserDao extends CrudDao<User> {
    UserTypeDTO getUserType(String email, String password);
}
