package dao.admin;

import entity.admin.AdminProperty;

public interface AdminDAO {

    void insertAdmin(AdminProperty adminProperty);

    void deleteAdmin(AdminProperty adminProperty);

    void updateAdmin(AdminProperty adminProperty);

    void getListFromServer();
}
