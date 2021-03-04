package example.service;

import example.model.Product;
import example.model.User;
import java.util.List;

public class BackToStockServiceImpl implements BackToStockService {
    private final StorageService storageService = new StorageServiceImpl();

    @Override
    public void subscribe(User user, Product product) {
        storageService.add(product, user);
    }

    @Override
    public List<User> subscribedUsers(Product product) {
        return storageService.getUsers(product);
    }
}
