package action.storageAction;

import dao.storage.StorageDAO;
import entity.storage.Storage;
import entity.storage.StorageProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class StorageActionl implements StorageAction{



    static ObservableList<StorageProperty> storagePropertiesList = FXCollections.observableArrayList();

    private StorageDAO storageDAO;


    public StorageActionl(StorageDAO storageDAO){
        this.storageDAO = storageDAO;
        this.storageDAO.getList();
    }


    public  ObservableList<StorageProperty> getObservableList() { return this.storagePropertiesList; }



    public static void setStorageList(ArrayList<Storage> list){
        storagePropertiesList.clear();

        for(Storage c : list){

            StorageProperty storageProperty = new StorageProperty();

            storageProperty.setName(c.getName());
            storageProperty.setNumber(c.getNumber());
            storageProperty.setUnit(c.getUnit());
            storageProperty.setCost(c.getCost());
            storageProperty.setCode(c.getCode());
            storagePropertiesList.add(storageProperty);
        }
    }

    @Override
    public void addStorage(StorageProperty storageProperty) {

        storagePropertiesList.add(storageProperty);
        storageDAO.insertStorage(storageProperty);
    }

    @Override
    public void deleteStorage(StorageProperty storageProperty) {
        storagePropertiesList.remove(storageProperty);
        storageDAO.deleteStorage(storageProperty);
    }


}
