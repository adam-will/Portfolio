package domain;

import clients.RestaurantApp;
import domain.employees.Employee;
import domain.employees.Waiter;
import domain.employees.WaiterShift;
import domain.items.*;
import domain.items.beverages.BeverageExtra;
import domain.items.beverages.CocaCola;
import domain.items.beverages.Coffee;
import domain.items.foods.FoodExtra;
import domain.items.foods.Gnocchi;
import domain.items.foods.Margherita;
import domain.orders.Order;
import domain.orders.OrderItem;
import domain.orders.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;
import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TakePayment {

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
        em1 = new Waiter("Michael", "Jordan", LocalDate.of(1963, 2, 17));
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
        RestaurantApp.addBeverageExtra(oi1, BeverageExtra.ICE);
        RestaurantApp.addBeverageExtra(oi1, BeverageExtra.ICE);
        RestaurantApp.addBeverageExtra(oi1, BeverageExtra.WHISKEY);
        oi3 = RestaurantApp.addItemToOrder(o1, i3);
        oi4 = RestaurantApp.addItemToOrder(o1, i4);
        RestaurantApp.addFoodExtra(oi4, FoodExtra.JALAPENOS);
        RestaurantApp.addFoodExtra(oi4, FoodExtra.JALAPENOS);
        RestaurantApp.addFoodExtra(oi4, FoodExtra.HAM);
        RestaurantApp.confirmOrder(o1);
    }

    @Test
    void closeOrderIfAmountPaidEqualsToTotalToPay() {
        RestaurantApp.takePayment(o1, w1, PaymentType.CARD, new BigDecimal("33.25"));
        assertFalse(o1.isOpen());
    }

    @Test
    void closeOrderIfAmountPaidLargerThanTotalToPay() {
        RestaurantApp.takePayment(o1, w1, PaymentType.CASH, new BigDecimal("34.25"));
        assertFalse(o1.isOpen());
    }

    @Test
    void doNotCloseOrderIfAmountPaidSmallerThanTotalToPay() {
        RestaurantApp.takePayment(o1, w1, PaymentType.CARD, new BigDecimal("32.25"));
        assertTrue(o1.isOpen());
    }

    @Test
    void setTableBackAsAvailableIfOrderPaid() {
        RestaurantApp.takePayment(o1, w1, PaymentType.CARD, new BigDecimal("34.25"));
        assertTrue(t1.checkTableAvailability() && t1.getCurrentOrder() == null);
    }

    @Test
    void restockUnconfirmedProductsWhenOrderClosed() {
        oi5 = RestaurantApp.addItemToOrder(o1, i5);
        int currentStockLevel = i5.getStockLevel();
        RestaurantApp.takePayment(o1, w1, PaymentType.CARD, new BigDecimal("34.25"));
        assertEquals(currentStockLevel + 1, i5.getStockLevel());
    }

    @Test
    void doNotAddPaymentIfOrderIsNotActive() {
        o1.setOpen(false);
        int paymentsBefore = w1.getPayments().size();
        RestaurantApp.takePayment(o1, w1, PaymentType.CARD, new BigDecimal("34.25"));
        assertEquals(paymentsBefore, w1.getPayments().size());
    }

    @Test
    void doNotAddPaymentIfValueEqualToZero() {
        int paymentsBefore = w1.getPayments().size();
        RestaurantApp.takePayment(o1, w1, PaymentType.CARD, new BigDecimal("0"));
        assertEquals(paymentsBefore, w1.getPayments().size());
    }

    @Test
    void doNotAddPaymentIfValueBelowZero() {
        int paymentsBefore = w1.getPayments().size();
        RestaurantApp.takePayment(o1, w1, PaymentType.CARD, new BigDecimal("-10"));
        assertEquals(paymentsBefore, w1.getPayments().size());
    }
}
