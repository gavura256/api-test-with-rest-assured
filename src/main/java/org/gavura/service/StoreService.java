package org.gavura.service;

public class StoreService extends CommonService {
    private static StoreService instance;

    public static StoreService getInstance() {
        if (instance == null) {
            instance = new StoreService();
        }

        return instance;
    }
}
