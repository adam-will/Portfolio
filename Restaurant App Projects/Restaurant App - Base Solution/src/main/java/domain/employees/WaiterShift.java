package domain.employees;

import domain.Table;
import domain.orders.Order;
import domain.orders.Payment;
import domain.orders.PaymentType;

import javax.management.InvalidAttributeValueException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 * A shift performed by a waiter.
 *
 * @author Adam Will
 */
public class WaiterShift {

    // variables
    private long id;
    private boolean active;
    private LocalDateTime timeStarted;
    private List<Order> activeOrders;
    private List<Payment> payments;
    private Employee employee;

    // constructors
    public WaiterShift(Employee employee) {
        this.active = true;
        this.timeStarted = LocalDateTime.now();
        this.activeOrders = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.employee = employee;
    }

    // methods

    /**
     * Creates a new order and assigns it against this waiter shift.
     *
     * @param table          Table assigned to the new order.
     * @param numberOfCovers Number of covers under the new order. Used for reporting.
     * @return New object of the Order class, linked to this object of the WaiterShift class.
     */
    public Order addNewOrder(Table table, int numberOfCovers) {
        Order newOrder = new Order(table, this, numberOfCovers);
        activeOrders.add(newOrder);
        return newOrder;
    }

    /**
     * Takes a payment against the order. Closes the order if fully paid.
     *
     * @param order       Order the payment is recorded against.
     * @param paymentType Type of payment.
     * @param amount      Amount recorded against the payment.
     * @throws IllegalStateException          Thrown when the payment is requested against an order that has already
     *                                        been closed.
     * @throws InvalidAttributeValueException Thrown when the payment value is equal to or lower than zero.
     */
    public void takePayment(Order order, PaymentType paymentType, BigDecimal amount) throws IllegalStateException,
            InvalidAttributeValueException {
        if (!order.isOpen()) throw new IllegalStateException("Cannot process payment against a closed order");
        if (amount.compareTo(new BigDecimal(0)) < 1)
            throw new InvalidAttributeValueException("Payment value must be larger than £0");
        this.getPayments().add(new Payment(order, paymentType, amount));
        order.closeIfPaid();
    }

    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(LocalDateTime timeStarted) {
        this.timeStarted = timeStarted;
    }

    public List<Order> getActiveOrders() {
        return activeOrders;
    }

    public void setActiveOrders(List<Order> activeOrders) {
        this.activeOrders = activeOrders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
