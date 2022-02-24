package dao.storage;

import entity.storage.Storage;

import java.util.ArrayList;

public interface StorageDAO {

    void insertStorage(Storage storage);

    void deleteStorage(Storage storage);
    ArrayList<Storage> getStorageList();


}
