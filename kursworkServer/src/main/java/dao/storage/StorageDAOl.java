package dao.storage;

import dao.DAOFactory;
import entity.storage.Storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StorageDAOl implements StorageDAO {

    public static dao.storage.StorageDAOl instance =new dao.storage.StorageDAOl();
    public static synchronized dao.storage.StorageDAOl getInstance(){
        return instance;
    }

    private StorageDAOl(){

    }

    private static Connection connection;

    static {
        connection = DAOFactory.getInstance().createConnection();
    }


    @Override
    public void insertStorage(Storage storage){

        String name = storage.getName();
        double number = storage.getNumber();
        String unit = storage.getUnit();
        double cost = storage.getCost();
        String code = storage.getCode();

        try{
            Statement statement=connection.createStatement();


            statement.executeUpdate("INSERT INTO storage.storages ( name, number, unit, cost, code) VALUES("
//                      + "' "+ id  + "' , "
                    + "' "+ name.trim() + "', "
                    + "' "+ number + "', "
                    + "' "+ unit.trim() + "', "
                    + "' "+ cost + "', "
                    + "' "+ code.trim() + "'); ");
            System.out.println("Info was added");




            System.out.println("Товар добавлен");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    @Override
    public void deleteStorage(Storage storage){
        System.out.println("Удаляем:  " + storage.getName() + "  " + storage.getCost());
        try{
            Statement statement=connection.createStatement();
            statement.executeUpdate(" DELETE FROM storage.storages " +
                    "WHERE name = " + "'" + storage.getName() + "';");

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("Удаление завершено! "+ storage.getName());
    }

    @Override
    public ArrayList<Storage> getStorageList(){
        ArrayList<Storage> storages = new ArrayList<Storage>();
        try{

            Statement statement = connection.createStatement();
            String command = "SELECT * FROM storage.storages";
            ResultSet rs = statement.executeQuery(command);
            try{
                while (rs.next()) {

                    Storage storage= new Storage();

                    storage.setName(rs.getString("name"));
                    storage.setNumber(rs.getDouble("number"));
                    storage.setUnit(rs.getString("unit"));
                    storage.setCost(rs.getDouble("cost"));
                    storage.setCode(rs.getString("code"));

                    storages.add(storage);
                }
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
            finally {
                rs.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return storages;
    }



}
