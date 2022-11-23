package domain;

import clients.RestaurantApp;
import domain.employees.Employee;
import domain.employees.Waiter;
import domain.employees.WaiterShift;
import domain.items.*;
import domain.items.beverage.CocaCola;
import domain.items.beverage.Coffee;
import domain.items.food.Gnocchi;
import domain.items.food.Margherita;
import domain.orders.Order;
import domain.orders.OrderItem;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;
import javax.naming.OperationNotSupportedException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfirmOrderTest {
    private static Employee em1;
    private static WaiterShift w1;
    private static Table t1;
    private static Order o1;
    private static Item i1;
    private static Item i2;
    private static Item i3;
    private static Item i4;
    private static Item i5;
    private static OrderItem oi1;
    private static OrderItem oi2;
    private static OrderItem oi3;
    private static OrderItem oi4;
    private static OrderItem oi5;

    @BeforeEach
    void setupEach() throws OperationNotSupportedException, InvalidAttributeValueException {
        em1 = new Waiter("Michael", "Jordan", LocalDate.of(1963, 2,17));
        w1 = new WaiterShift(em1);
        t1 = new Table(7);
        i1 = new CocaCola();
        i2 = new Coffee();
        i3 = new Gnocchi();
        i4 = new Gnocchi();
        i5 = new Margherita();
        i1.setStockLevel(1);
        i2.setStockLevel(1);
        i3.setStockLevel(2);
        i5.setStockLevel(1);
        o1 = RestaurantApp.openOrder(t1, w1, 6);
        oi1 = RestaurantApp.addItemToOrder(o1, i1);
        oi2 = RestaurantApp.addItemToOrder(o1, i2);
        oi3 = RestaurantApp.addItemToOrder(o1, i3);
        oi4 = RestaurantApp.addItemToOrder(o1, i4);
        oi5 = RestaurantApp.addItemToOrder(o1, i5);
        RestaurantApp.addBeverageExtra(oi2, BeverageDecoratorType.MILK);
        RestaurantApp.addBeverageExtra(oi1, BeverageDecoratorType.ICE);
        RestaurantApp.addBeverageExtra(oi1, BeverageDecoratorType.ICE);
        RestaurantApp.addBeverageExtra(oi1, BeverageDecoratorType.WHISKEY);
        RestaurantApp.addFoodExtra(oi4, FoodDecoratorType.JALAPENOES);
        RestaurantApp.addFoodExtra(oi4, FoodDecoratorType.JALAPENOES);
        RestaurantApp.addFoodExtra(oi4, FoodDecoratorType.HAM);
        RestaurantApp.addFoodExtra(oi5, FoodDecoratorType.MOZARELLA);
    }

    // No unconfirmed products left on the order
    @Test
    void noUnconfirmedProductsLeftOnOrder() {
        RestaurantApp.confirmOrder(o1);
        assertEquals(o1.getOrderItems().stream().filter(e -> !e.isConfirmed()).count(), 0);
    }

//    // Compare the message output for the printer
//    @Test
//    void validMessageProducedByPrinter(){
//        String expectedResult = "Time: 09-06-2022 16:12:27\n" +
//                "Table: 7\n" +
//                "============================\n" +
//                "Bar Area:\n" +
//                "Sprite\n" +
//                "    * Ice x 2\n" +
//                "    * Jack Daniels x 1\n" +
//                "Coffee\n" +
//                "    * Steamed Milk x 1\n" +
//                "\n" +
//                "Kitchen Area:\n" +
//                "Gnocchi\n" +
//                "Gnocchi\n" +
//                "    * Jalapenoes x 2\n" +
//                "    * Ham x 1\n" +
//                "Margherita\n" +
//                "    * Diced Mozzarella x 1\n" +
//                "============================";
//        assertEquals(RestaurantApp.confirmOrder(o1), expectedResult);
//    }
}