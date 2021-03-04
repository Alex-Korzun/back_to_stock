package example.service;

import example.model.Product;
import example.model.User;
import java.util.List;

public interface StorageService {
    void add(Product product, User user);

    List<User> getUsers(Product product);
}
