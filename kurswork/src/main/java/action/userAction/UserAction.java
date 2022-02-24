package action.userAction;

import entity.user.UserProperty;

public interface UserAction {

    void add(UserProperty userProperty);

    void update(UserProperty userProperty);

    void delete(UserProperty userProperty);
}
