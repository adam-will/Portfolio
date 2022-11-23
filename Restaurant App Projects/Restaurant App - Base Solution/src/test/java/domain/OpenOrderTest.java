package domain;

import clients.RestaurantApp;
import domain.employees.Employee;
import domain.employees.Waiter;
import domain.employees.WaiterShift;
import domain.orders.Order;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class OpenOrderTest {

    private static Employee em1;
    private static Employee em2;
    private static Employee em3;
    private static Employee em4;
    private static Employee em5;
    private static WaiterShift w1;
    private static WaiterShift w2;
    private static WaiterShift w3;
    private static WaiterShift w4;
    private static WaiterShift w5;
    private static Table t1;
    private static Table t2;
    private static Table t3;
    private static Table t4;
    private static Table t5;

    @BeforeAll
    static void setup() {
        em1 = new Waiter("Michael", "Jordan", LocalDate.of(1963, 2,17));
        em2 = new Waiter("Jack", "Jordan", LocalDate.of(1963, 2,17));
        em3 = new Waiter("Tommy", "Jordan", LocalDate.of(1963, 2,17));
        em4 = new Waiter("Oliver", "Jordan", LocalDate.of(1963, 2,17));
        em5 = new Waiter("Alex", "Jordan", LocalDate.of(1963, 2,17));
        w1 = new WaiterShift(em1);
        w2 = new WaiterShift(em2);
        w3 = new WaiterShift(em3);
        w4 = new WaiterShift(em4);
        w5 = new WaiterShift(em5);
        t1 = new Table(1);
        t2 = new Table(2);
        t3 = new Table(3);
        t4 = new Table(4);
        t5 = new Table(5);

    }

    @Test
    void newOrderCreated() {
        assertNotNull(RestaurantApp.openOrder(t1, w1, 4));
    }

    @Test
    void tableSetToNotAvailable() {
        RestaurantApp.openOrder(t2, w2, 5);
        assertFalse(t2.checkTableAvailability());
    }

    @Test
    void tableLinkedToNewOrder() {
        Order currentOrder = RestaurantApp.openOrder(t3, w3, 3);
        assertEquals(t3.getCurrentOrder(), currentOrder);
    }

    @Test
    void throwsExceptionIfTableNotAvailable() {
        RestaurantApp.openOrder(t4, w4, 3);
        assertThrows(IllegalStateException.class, () -> {
            RestaurantApp.openOrder(t4, w4, 9);
        });
    }

    @Test
    void throwsExceptionIfWaiterShiftNotActive() {
        w5.setActive(false);
        assertThrows(IllegalStateException.class, () -> {
            RestaurantApp.openOrder(t5, w5, 4);
        });

    }
}