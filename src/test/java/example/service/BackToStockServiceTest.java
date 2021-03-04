package example.service;

import example.model.Product;
import example.model.ProductCategory;
import example.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class BackToStockServiceTest {
    private final BackToStockService backToStockService = new BackToStockServiceImpl();

    @BeforeAll
    static void beforeAll() {

    }

    @Test
    void testSubscribeWithMedicine_Ok() {
        User userWithPremium = new User();
        userWithPremium.setName("Bob");
        userWithPremium.setAge(20);
        userWithPremium.setPremium(true);

        User userWithBigAgeAndMedicine = new User();
        userWithBigAgeAndMedicine.setName("Alice");
        userWithBigAgeAndMedicine.setAge(72);
        userWithBigAgeAndMedicine.setPremium(false);

        User userWithBigAgeAndRegularProduct = new User();
        userWithBigAgeAndRegularProduct.setName("John");
        userWithBigAgeAndRegularProduct.setAge(72);
        userWithBigAgeAndRegularProduct.setPremium(false);

        User userWithLowPriority = new User();
        userWithLowPriority.setName("Max");
        userWithLowPriority.setAge(45);
        userWithLowPriority.setPremium(false);

        Product medicine = new Product("1", ProductCategory.MEDICAL);
        Product digital = new Product("2", ProductCategory.DIGITAL);

        List<User> expected = List.of(userWithPremium, userWithBigAgeAndMedicine, userWithLowPriority);

        backToStockService.subscribe(userWithBigAgeAndRegularProduct, digital);
        backToStockService.subscribe(userWithPremium, medicine);
        backToStockService.subscribe(userWithLowPriority, medicine);
        backToStockService.subscribe(userWithBigAgeAndMedicine, medicine);

        List<User> actual = backToStockService.subscribedUsers(medicine);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSubscribeWithRegularProducts_Ok() {
        User userWithPremium = new User();
        userWithPremium.setName("Bob");
        userWithPremium.setAge(20);
        userWithPremium.setPremium(true);

        User userWithLowPriority = new User();
        userWithLowPriority.setName("Max");
        userWithLowPriority.setAge(45);
        userWithLowPriority.setPremium(false);

        User userWithBigAge = new User();
        userWithBigAge.setName("Alice");
        userWithBigAge.setAge(72);
        userWithBigAge.setPremium(false);

        Product digital = new Product("1", ProductCategory.DIGITAL);

        List<User> expected = List.of(userWithPremium, userWithBigAge, userWithLowPriority);

        backToStockService.subscribe(userWithBigAge, digital);
        backToStockService.subscribe(userWithLowPriority, digital);
        backToStockService.subscribe(userWithPremium, digital);

        List<User> actual = backToStockService.subscribedUsers(digital);
        Assertions.assertEquals(expected, actual);
    }
}