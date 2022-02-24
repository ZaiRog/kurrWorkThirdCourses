package action.adminAction;

import dao.admin.AdminDAO;
import entity.admin.Admin;
import entity.admin.AdminProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class AdminActionl implements AdminAction{

    private static ObservableList<AdminProperty> adminPropertyList = FXCollections.observableArrayList();

    private static ArrayList<Admin> adminArrayList = new ArrayList<Admin>();

    private AdminDAO adminDAO;
    private static boolean checkResult;

    public AdminActionl(AdminDAO adminDAO){
        this.adminDAO=adminDAO;
        this.adminDAO.getListFromServer();
    }

    @Override
    public boolean isCheckResult(){
        return checkResult;
    }

    public static void setCheckresult(boolean info){
        checkResult=info;
    }

    @Override
    public void addAdmin(AdminProperty adminProperty){
        if(!(adminProperty.getLogin().equals("") &&
                adminProperty.getPassword().equals("") &&
                adminProperty.getName().equals("") &&
                adminProperty.getSurname().equals("")
        )){
            adminPropertyList.add(adminProperty);
            adminDAO.insertAdmin(adminProperty);
        }
    }




    public ObservableList<AdminProperty> getAdminList(){
        return this.adminPropertyList;
    }


    public static ArrayList<Admin> getAdminArrayList(){
        return adminArrayList;
    }

    public static void setAdminPropertyList(ArrayList<Admin> list){
        adminPropertyList.clear();
        adminArrayList.clear();
        adminArrayList=list;
        for(Admin c: list){
            AdminProperty adminProperty=new AdminProperty();
            adminProperty.setId(c.getId());
            adminProperty.setLogin(c.getLogin());
            adminProperty.setPassword(c.getPassword());
            adminProperty.setName(c.getName());
            adminProperty.setSurname(c.getSurname());
            adminPropertyList.add(adminProperty);
        }
    }

    @Override
    public void update(AdminProperty adminProperty){
        adminDAO.updateAdmin(adminProperty);
    }

    @Override
    public void deleteAdmin(AdminProperty adminProperty){
        adminPropertyList.remove(adminProperty);
        adminDAO.deleteAdmin(adminProperty);
    }

}
