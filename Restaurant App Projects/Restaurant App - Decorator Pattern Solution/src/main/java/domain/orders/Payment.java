package domain.orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A payment taken against the order. Once the sum of payments taken against an order exceed its value, the order is
 * closed.
 *
 * @author Adam Will
 */
public class Payment {

    // variables
    private long id;
    private Order order;
    private LocalDateTime timePaymentReceived;
    private PaymentType paymentType;
    private BigDecimal amount;

    // constructors
    public Payment(Order order, PaymentType paymentType, BigDecimal amount) {
        this.order = order;
        this.paymentType = paymentType;
        this.amount = amount;
        this.timePaymentReceived = LocalDateTime.now();
    }

    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalDateTime getTimePaymentReceived() {
        return timePaymentReceived;
    }

    public void setTimePaymentReceived(LocalDateTime timePaymentReceived) {
        this.timePaymentReceived = timePaymentReceived;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
