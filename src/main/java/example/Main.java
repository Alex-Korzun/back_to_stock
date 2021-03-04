package example;

import example.model.Product;
import example.model.ProductCategory;
import example.model.User;
import example.service.BackToStockService;
import example.service.BackToStockServiceImpl;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setName("Bob");
        user.setAge(20);
        user.setPremium(true);

        User user2 = new User();
        user2.setName("Alice");
        user2.setAge(72);
        user2.setPremium(false);

        User user3 = new User();
        user3.setName("John");
        user3.setAge(72);
        user3.setPremium(false);

        User user4 = new User();
        user4.setName("Max");
        user4.setAge(45);
        user4.setPremium(false);

        Product books = new Product("1", ProductCategory.BOOKS);
        Product medicine = new Product("2", ProductCategory.MEDICAL);
        Product digital = new Product("3", ProductCategory.DIGITAL);

        //StorageService storageService = new StorageServiceImpl();
        BackToStockService backToStockService = new BackToStockServiceImpl();

        backToStockService.subscribe(user, medicine);
        backToStockService.subscribe(user2, medicine);
        backToStockService.subscribe(user3, medicine);
        backToStockService.subscribe(user4, medicine);

        //System.out.println(backToStockService.subscribedUsers(books).toString());
        System.out.println(backToStockService.subscribedUsers(medicine).toString());

    }
}
