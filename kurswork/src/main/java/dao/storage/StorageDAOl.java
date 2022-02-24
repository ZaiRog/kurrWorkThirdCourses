package dao.storage;

import communication.Communication;
import communication.Communicationl;
import connection.ConnectionInfo;
import entity.storage.Storage;
import entity.storage.StorageProperty;

public class StorageDAOl implements StorageDAO{

    public static dao.storage.StorageDAOl instance=new dao.storage.StorageDAOl();
    public static dao.storage.StorageDAOl getInstance(){
        return instance;
    }

    private StorageDAOl() { }

    private ConnectionInfo connectionInfo;

    public StorageDAOl(ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }



    @Override
    public void insertStorage(StorageProperty storageProperty) {
        System.out.println("Отправка: "+ storageProperty);


        Storage storage = new Storage();
        storage.setName(storageProperty.getName());
        storage.setNumber(storageProperty.getNumber());
        storage.setUnit(storageProperty.getUnit());
        storage.setCost(storageProperty.getCost());
        storage.setCode(storageProperty.getCode());

        Communication msq= new Communicationl(Communication.ADD_NEW_PRODUCT,storage);
        connectionInfo.send(msq);
    }

    @Override
    public void deleteStorage(StorageProperty storageProperty) {

        Storage storage = new Storage();
        storage.setName(storageProperty.getName());
        storage.setNumber(storageProperty.getNumber());
        storage.setUnit(storageProperty.getUnit());
        storage.setCost(storageProperty.getCost());
        storage.setCode(storageProperty.getCode());
        Communication msq = new Communicationl(Communication.DELETE_PRODUCT,storage);
        connectionInfo.send(msq);
    }



    @Override
    public void getList() {
        System.out.println("ждем список");
        Communication mes = new Communicationl(Communication.SHOW_ALL_PRODUCT);
        connectionInfo.send(mes);
    }

}
