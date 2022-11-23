package domain;

import clients.RestaurantApp;
import domain.employees.Employee;
import domain.employees.Waiter;
import domain.employees.WaiterShift;
import domain.items.Item;
import domain.items.food.Margherita;
import domain.orders.Order;
import domain.orders.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AddItemToOrderTest {

    private static Employee em1;
    private static WaiterShift w1;
    private static Table t1;
    private static Order o1;
    private static Item i1;

    @BeforeEach
    void setupEach() {
        em1 = new Waiter("Michael", "Jordan", LocalDate.of(1963, 2,17));
        w1 = new WaiterShift(em1);
        t1 = new Table(4);
        o1 = RestaurantApp.openOrder(t1, w1, 7);
        i1 = new Margherita();
        i1.setStockLevel(1);
    }

    // If successful xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    @Test
    void newOrderItemCreated() {
        assertNotNull(RestaurantApp.addItemToOrder(o1, i1));
    }

    @Test
    void newOrderItemAddedToOrderOrderItemsList() {
        int orderItemNumber = o1.getOrderItems().size();
        RestaurantApp.addItemToOrder(o1, i1);
        assertEquals(o1.getOrderItems().size(), orderItemNumber + 1);
    }

    @Test
    void stockLevelDecreasedByOne() {
        RestaurantApp.addItemToOrder(o1, i1);
        assertEquals(i1.getStockLevel(), 0);
    }

    @Test
    void newOrderItemReferencesOrder() {
        OrderItem newOrderItem = RestaurantApp.addItemToOrder(o1, i1);
        assertTrue(o1.getOrderItems().contains(newOrderItem));
    }

    @Test
    void newOrderItemReferencesItem() {
        OrderItem newOrderItem = RestaurantApp.addItemToOrder(o1, i1);
        assertEquals(newOrderItem.getItem(), i1);
    }

    //   If fails xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    @Test
    void exceptionThrownIfOrderClosed() {
        o1.setOpen(false);
        assertThrows(IllegalStateException.class, () -> {
            RestaurantApp.addItemToOrder(o1, i1);
        });
    }

    @Test
    void exceptionThrownIfStockLevelBelowOne() {
        i1.setStockLevel(-1);
        assertThrows(IllegalStateException.class, () -> {
            RestaurantApp.addItemToOrder(o1, i1);
        });
    }

    @Test
    void stockLevelNotChangedIfOrderNotActiveOrStockLevelBelowOne() {
        i1.setStockLevel(-1);
        o1.setOpen(false);
        try {
            RestaurantApp.addItemToOrder(o1, i1);
        } catch (IllegalStateException e) {

        } finally {
            assertEquals(i1.getStockLevel(), -1);
        }
    }
}
