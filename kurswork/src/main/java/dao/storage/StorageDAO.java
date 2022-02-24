package dao.storage;

import entity.storage.StorageProperty;

public interface StorageDAO {

    void insertStorage(StorageProperty storageProperty);

    void deleteStorage(StorageProperty storageProperty);

    void getList();
}
