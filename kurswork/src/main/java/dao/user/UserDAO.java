package dao.user;

import entity.user.UserProperty;

public interface UserDAO {

    void insert(UserProperty userProperty);

    void delete(UserProperty userProperty);

    void update(UserProperty userProperty);

    void getListFromServer();
}
