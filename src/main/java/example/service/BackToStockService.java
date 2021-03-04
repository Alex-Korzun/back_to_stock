package example.service;

import example.model.Product;
import example.model.User;
import java.util.List;

public interface BackToStockService {
    void subscribe(User user, Product product);

    List<User> subscribedUsers(Product product);
}
