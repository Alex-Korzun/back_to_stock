package example.service;

import example.model.Product;
import example.model.ProductCategory;
import example.model.Storage;
import example.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageServiceImpl implements StorageService {
    private final Map<Product, Storage> productStorageHashMap = new HashMap<>();

    @Override
    public void add(Product product, User user) {
        if (!productStorageHashMap.containsKey(product)) {
            productStorageHashMap.put(product, new Storage());
        }
        prioritize(product, user);
    }

    @Override
    public List<User> getUsers(Product product) {
        List<User> users = new ArrayList<>();
        Storage storage = productStorageHashMap.get(product);
        users.addAll(storage.getHighPriorityUsers());
        users.addAll(storage.getMediumPriorityUsers());
        users.addAll(storage.getLowPriorityUsers());
        return users;
    }

    private void prioritize(Product product, User user) {
        Storage storage = productStorageHashMap.get(product);
        if (user.isPremium() || (user.getAge() >= 70
                && product.getCategory().equals(ProductCategory.MEDICAL))) {
            addHighPriorityUser(storage, user);
        } else if (user.getAge() >= 70) {
            addMediumPriorityUser(storage, user);
        } else {
            addLowPriorityUser(storage, user);
        }
    }

    private void addHighPriorityUser(Storage storage, User user) {
        storage.getHighPriorityUsers().add(user);
    }

    private void addMediumPriorityUser(Storage storage, User user) {
        storage.getMediumPriorityUsers().add(user);
    }

    private void addLowPriorityUser(Storage storage, User user) {
        storage.getLowPriorityUsers().add(user);
    }

}
