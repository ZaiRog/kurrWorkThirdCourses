package dao.admin;

import communication.Communication;
import communication.Communicationl;
import connection.ConnectionInfo;
import entity.admin.Admin;
import entity.admin.AdminProperty;

public class AdminDAOl implements AdminDAO {

    public static AdminDAOl instance=new AdminDAOl();

    public static AdminDAOl getInstance(){
        return instance;
    }

    private AdminDAOl(){}

    private ConnectionInfo connectionInfo;

    public AdminDAOl(ConnectionInfo connectionInfo){
        this.connectionInfo=connectionInfo;
    }


    public void getListFromServer(){
        System.out.println("Ждём список");
        Communication mes=new Communicationl(Communication.SHOW_ALL_ADMIN);
        connectionInfo.send(mes);
    }

    @Override
    public void insertAdmin(AdminProperty adminProperty){

        System.out.println("Отправка: "+ adminProperty);

        Admin admin=new Admin();
        admin.setId(adminProperty.getId());
        admin.setLogin(adminProperty.getLogin());
        admin.setPassword(adminProperty.getPassword());
        admin.setName(adminProperty.getName());
        admin.setSurname(adminProperty.getSurname());

        Communication msq = new Communicationl(admin, Communication.ADD_NEW_ADMIN);
        connectionInfo.send(msq);
    }


    @Override
    public void updateAdmin(AdminProperty adminProperty) {

        Admin admin = new Admin();
        admin.setId(adminProperty.getId());
        admin.setLogin(adminProperty.getLogin());
        admin.setPassword(adminProperty.getPassword());
        admin.setName(adminProperty.getName());
        admin.setSurname(adminProperty.getSurname());

        Communication msq = new Communicationl(admin, Communication.UPDATE_ADMIN);
        connectionInfo.send(msq);
    }



    @Override
    public void deleteAdmin(AdminProperty adminProperty) {
        Admin admin = new Admin();
        admin.setId(adminProperty.getId());
        admin.setLogin(adminProperty.getLogin());
        admin.setPassword(adminProperty.getPassword());
        admin.setName(adminProperty.getName());
        admin.setSurname(adminProperty.getSurname());

        Communication msq = new Communicationl(admin, Communication.DELETE_ADMIN);
        connectionInfo.send(msq);
    }


}
