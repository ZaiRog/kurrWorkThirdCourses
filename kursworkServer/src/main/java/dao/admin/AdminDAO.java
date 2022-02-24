package dao.admin;

import entity.admin.Admin;

import java.util.ArrayList;

public interface AdminDAO {

    void insertAdmin(Admin admin);

    void deleteAdmin(Admin admin);

    void update(Admin admin);
    ArrayList<Admin> getAdminList();

}
