package action.adminAction;

import entity.admin.AdminProperty;

public interface AdminAction {

    void addAdmin(AdminProperty adminProperty);

    boolean isCheckResult();

    void update(AdminProperty adminProperty);

    void deleteAdmin(AdminProperty adminProperty);
}
