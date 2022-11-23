package domain;

import clients.RestaurantApp;
import domain.employees.*;
import domain.items.*;
import domain.items.beverage.CocaCola;
import domain.items.beverage.Coffee;
import domain.items.food.Gnocchi;
import domain.items.food.Margherita;
import domain.orders.Order;
import domain.orders.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import javax.management.InvalidAttributeValueException;
import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class RemoveItemFromOrder {

    private static Employee em1;
    private static Employee em2;
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
        em1 = new Waiter("Michael", "Jordan", LocalDate.of(1963, 2, 17));
        em2 = new Manager("Gunther", "Steiner", LocalDate.of(1960, 6, 5));
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
        RestaurantApp.addBeverageExtra(oi1, BeverageDecoratorType.ICE);
        RestaurantApp.addBeverageExtra(oi1, BeverageDecoratorType.ICE);
        RestaurantApp.addBeverageExtra(oi1, BeverageDecoratorType.WHISKEY);
        oi3 = RestaurantApp.addItemToOrder(o1, i3);
        oi4 = RestaurantApp.addItemToOrder(o1, i4);
        RestaurantApp.addFoodExtra(oi4, FoodDecoratorType.JALAPENOES);
        RestaurantApp.addFoodExtra(oi4, FoodDecoratorType.JALAPENOES);
        RestaurantApp.addFoodExtra(oi4, FoodDecoratorType.HAM);
        RestaurantApp.confirmOrder(o1);
        oi5 = RestaurantApp.addItemToOrder(o1, i5);
        RestaurantApp.addFoodExtra(oi5, FoodDecoratorType.MOZARELLA);
        oi2 = RestaurantApp.addItemToOrder(o1, i2);
        RestaurantApp.addBeverageExtra(oi2, BeverageDecoratorType.MILK);
    }

    @Test
    void removeUnconfirmedItemIfEmployeeIsNull() {
        RestaurantApp.removeItemFromOrder(null, o1, oi2);
        assertFalse(o1.getOrderItems().contains(oi2));
    }

    @Test
    void doNotRemoveConfirmedItemIfEmployeeObjectNull() {
        RestaurantApp.removeItemFromOrder(null, o1, oi4);
        assertTrue(o1.getOrderItems().contains(oi4));
    }

    @Test
    void removeUnconfirmedItemIfEmployeeAccessLevelLow() {
        RestaurantApp.removeItemFromOrder(em1, o1, oi2);
        assertFalse(o1.getOrderItems().contains(oi2));
    }

    @Test
    void doNotRemoveConfirmedItemIfEmployeeAccessLevelLow() {
        RestaurantApp.removeItemFromOrder(em1, o1, oi4);
        assertTrue(o1.getOrderItems().contains(oi4));
    }

    @Test
    void removeConfirmedItemIfEmployeeAccessLevelMedium() {
        RestaurantApp.removeItemFromOrder(em2, o1, oi4);
        assertFalse(o1.getOrderItems().contains(oi4));
    }

    @Test
    void removeConfirmedItemIfEmployeeAccessLevelHigh() {
        em2.setAccessLevel(AccessLevel.HIGH);
        System.out.println(o1.getOrderItems().contains(oi4));
        RestaurantApp.removeItemFromOrder(em2, o1, oi4);
        assertFalse(o1.getOrderItems().contains(oi4));
    }

    @Test
    void increaseStockLevelWhenUnconfirmedItemRemoved() {
        int previousStockLevel = oi2.getItem().getStockLevel();
        RestaurantApp.removeItemFromOrder(em1, o1, oi2);
        assertEquals(previousStockLevel + 1, oi2.getItem().getStockLevel());
    }

    @Test
    void doNotIncreaseStockLevelWhenConfirmedItemRemoved() {
        int previousStockLevel = oi4.getItem().getStockLevel();
        RestaurantApp.removeItemFromOrder(em2, o1, oi4);
        assertEquals(previousStockLevel, oi4.getItem().getStockLevel());
    }
}
