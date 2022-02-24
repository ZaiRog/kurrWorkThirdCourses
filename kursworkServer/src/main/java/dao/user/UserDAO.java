package dao.user;

import entity.user.User;

import java.util.ArrayList;

public interface UserDAO {

    void insert(User user);

    void delete(User user);

    void update(User user);
    ArrayList<User> getUserList();
}
