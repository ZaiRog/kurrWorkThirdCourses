package communication;

import entity.admin.Admin;
import entity.orders.Orders;
import entity.storage.Storage;
import entity.user.User;

import java.util.ArrayList;
import java.util.Objects;

public class Communicationl implements  Communication{

    static final long serialVersionUID=1L;
    private int type;
    private int id;

    private ArrayList<Admin> adminList;
    private Admin admin;

    private ArrayList<User> userList;
    private User user;

    private ArrayList<Storage> storageList;
    private Storage storage;

    private ArrayList<Orders> ordersArrayList;
    private Orders orders;


    private String login;
    private String password;
    private boolean checkResult;

    public Communicationl(int type, boolean checkResult){
        this.type = type;
        this.checkResult = checkResult;
    }

    public Communicationl(int type, String login, String password){
        this.type=type;
        this.login=login;
        this.password=password;
    }

    public Communicationl(int type){ this.type = type;}

    public Communicationl(int type, int id){
        this.type=type;
        this.id = id;
    }

    public Communicationl(int type, Storage storage) {
        this.type = type;
        this.storage = storage;
    }


    public Communicationl(int type, ArrayList<Storage> storageList, int fake, int fake2) {
        this.type = type;
        this.storageList = storageList;
    }


    public Communicationl(int type, ArrayList<Orders> ordersArrayList, int fake){
        this.type = type;
        this.ordersArrayList = ordersArrayList;
    }

    public Communicationl(int type, Orders orders){
        this.type = type;
        this.orders = orders;
    }



    public Communicationl(ArrayList<Admin> adminList, int type){
        super();
        this.adminList = adminList;
        this.type=type;
    }

    public Communicationl(Admin admin, int type){
        super();
        this.admin=admin;
        this.type=type;
    }

    public Communicationl(int type, ArrayList<User> userList){
        super();
        this.userList = userList;
        this.type = type;
    }

    public Communicationl(User user, int type){
        super();
        this.user= user;
        this.type=type;
    }


    public void setUserList(ArrayList<User> userList){
        this.userList= userList;
    }

    public void setUser(User user){ this.user = user;}

    @Override
    public ArrayList<User> getUserList() {
        return userList;
    }

    @Override
    public User getUser() {
        return user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCheckResult() {
        return checkResult;
    }

    public void setCheckResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    @Override
    public ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(ArrayList<Admin> adminList) {
        this.adminList = adminList;
    }

    @Override
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }


    public void setStorageList(ArrayList<Storage> storageList) {
        this.storageList = storageList;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public ArrayList<Storage> getStorageList() {
        return storageList;
    }

    @Override
    public Storage getStorage() {
        return storage;
    }


    public ArrayList<Orders> getOrdersList() {
        return ordersArrayList;
    }

    public void setOrdersList(ArrayList<Orders> ordersArrayList) {
        this.ordersArrayList = ordersArrayList;
    }

    @Override
    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Communicationl message = (Communicationl) o;
        return type == message.type &&
                id == message.id &&
                checkResult == message.checkResult &&
                Objects.equals(adminList, message.adminList) &&
                Objects.equals(admin, message.admin) &&
                Objects.equals(storageList, message.storageList) &&
                Objects.equals(storage, message.storage) &&
                Objects.equals(ordersArrayList, message.ordersArrayList) &&
                Objects.equals(orders, message.orders) &&
                Objects.equals(login, message.login) &&
                Objects.equals(password, message.password);
    }

    @Override
    public int hashCode() {
//        return Objects.hash(type, id, adminList, admin, storageList, storage, orderList, order, login, password, checkResult);
        return Objects.hash(type, id, adminList, admin, storageList, storage, ordersArrayList, orders, login, password, checkResult);
    }

}
