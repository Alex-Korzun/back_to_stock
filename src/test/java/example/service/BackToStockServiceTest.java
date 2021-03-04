package example.service;

import example.model.Product;
import example.model.ProductCategory;
import example.model.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BackToStockServiceTest {
    private final BackToStockService backToStockService = new BackToStockServiceImpl();

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

        User userWithLowPriority = new User();
        userWithLowPriority.setName("Max");
        userWithLowPriority.setAge(45);
        userWithLowPriority.setPremium(false);

        Product medicine = new Product("1", ProductCategory.MEDICAL);

        backToStockService.subscribe(userWithPremium, medicine);
        backToStockService.subscribe(userWithLowPriority, medicine);
        backToStockService.subscribe(userWithBigAgeAndMedicine, medicine);

        List<User> expected = List.of(userWithPremium, userWithBigAgeAndMedicine, userWithLowPriority);
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

        backToStockService.subscribe(userWithBigAge, digital);
        backToStockService.subscribe(userWithLowPriority, digital);
        backToStockService.subscribe(userWithPremium, digital);

        List<User> expected = List.of(userWithPremium, userWithBigAge, userWithLowPriority);
        List<User> actual = backToStockService.subscribedUsers(digital);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSubscribeWithDifferentProducts_Ok() {
        User userWithPremiumAndMedicine = new User();
        userWithPremiumAndMedicine.setName("Pepper");
        userWithPremiumAndMedicine.setAge(20);
        userWithPremiumAndMedicine.setPremium(true);

        User userWithPremiumAndRegularProduct = new User();
        userWithPremiumAndRegularProduct.setName("Peter");
        userWithPremiumAndRegularProduct.setAge(45);
        userWithPremiumAndRegularProduct.setPremium(true);

        User userWithBigAgeAndMedicine = new User();
        userWithBigAgeAndMedicine.setName("Steve");
        userWithBigAgeAndMedicine.setAge(70);
        userWithBigAgeAndMedicine.setPremium(false);

        User userUnderSeventyWithMedicine = new User();
        userUnderSeventyWithMedicine.setName("Tony");
        userUnderSeventyWithMedicine.setAge(69);
        userUnderSeventyWithMedicine.setPremium(false);

        User userWithBigAgeAndRegularProduct = new User();
        userWithBigAgeAndRegularProduct.setName("James");
        userWithBigAgeAndRegularProduct.setAge(72);
        userWithBigAgeAndRegularProduct.setPremium(false);

        User userWithLowPriorityAndMedicine = new User();
        userWithLowPriorityAndMedicine.setName("Bruce");
        userWithLowPriorityAndMedicine.setAge(45);
        userWithLowPriorityAndMedicine.setPremium(false);

        User userWithLowPriorityAndRegularProduct = new User();
        userWithLowPriorityAndRegularProduct.setName("Jarvis");
        userWithLowPriorityAndRegularProduct.setAge(32);
        userWithLowPriorityAndRegularProduct.setPremium(false);

        Product medicine = new Product("1", ProductCategory.MEDICAL);
        Product books = new Product("2", ProductCategory.BOOKS);
        Product digital = new Product("3", ProductCategory.DIGITAL);

        backToStockService.subscribe(userWithLowPriorityAndRegularProduct, books);
        backToStockService.subscribe(userWithLowPriorityAndRegularProduct, digital);
        backToStockService.subscribe(userWithPremiumAndMedicine, medicine);
        backToStockService.subscribe(userWithBigAgeAndMedicine, medicine);
        backToStockService.subscribe(userWithPremiumAndRegularProduct, digital);
        backToStockService.subscribe(userWithPremiumAndRegularProduct, books);
        backToStockService.subscribe(userWithBigAgeAndRegularProduct, books);
        backToStockService.subscribe(userUnderSeventyWithMedicine, medicine);
        backToStockService.subscribe(userWithLowPriorityAndMedicine, medicine);

        List<User> medicineExpected = List.of(userWithPremiumAndMedicine, userWithBigAgeAndMedicine,
                userUnderSeventyWithMedicine, userWithLowPriorityAndMedicine);
        List<User> medicineActual = backToStockService.subscribedUsers(medicine);
        Assertions.assertEquals(medicineExpected, medicineActual);

        List<User> booksExpected = List.of(userWithPremiumAndRegularProduct,
                userWithBigAgeAndRegularProduct, userWithLowPriorityAndRegularProduct);
        List<User> booksActual = backToStockService.subscribedUsers(books);
        Assertions.assertEquals(booksExpected, booksActual);

        List<User> digitalExpected = List.of(userWithPremiumAndRegularProduct, userWithLowPriorityAndRegularProduct);
        List<User> digitalActual = backToStockService.subscribedUsers(digital);
        Assertions.assertEquals(digitalExpected, digitalActual);
    }
}