package domain.orders;

import domain.Table;
import domain.employees.AccessLevel;
import domain.employees.Employee;
import domain.employees.WaiterShift;
import domain.items.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

/**
 * An order. Assigned against a waiter and a table. Lists ordered items.
 *
 * @author Adam Will
 */
public class Order {

    // variables
    private UUID id = UUID.randomUUID();
    private Table table;
    private int numberOfcovers;
    private boolean open;
    private WaiterShift owner;
    private ArrayList<OrderItem> orderItems;

    // constructors
    public Order(Table table, WaiterShift owner, int numberOfCovers) {
        this.table = table;
        this.owner = owner;
        this.numberOfcovers = numberOfCovers;
        this.orderItems = new ArrayList<>();
        this.open = true;
        this.table.setCurrentOrder(this);
    }

    // methods

    /**
     * Adds an item to the order.
     *
     * @param item Item to be added to the order
     * @return New object of the OrderItem class, linked to this object of the Order class.
     */
    public OrderItem addItemToOrder(Item item) {
        OrderItem newOrderItem = new OrderItem(this, item);
        this.getOrderItems().add(newOrderItem);
        return newOrderItem;
    }

    /**
     * Removes the selected item from the selected order. Restricts the outcome based on the user's access level. If
     * selected order item has already been confirmed (sent for processing) and the user's access level is not
     * sufficient - throws the IllegalCallerException.
     *
     * @param employee  User who requested for the item to be removed
     * @param orderItem Item to be removed
     * @throws IllegalCallerException Thrown when the user has insufficient access level for the requested action
     */
    public void removeItemFromOrder(Employee employee, OrderItem orderItem) throws IllegalCallerException {
        if (orderItem.isConfirmed() && !(employee == null) &&
                (employee.getAccessLevel() == AccessLevel.MEDIUM || employee.getAccessLevel() == AccessLevel.HIGH)) {
            this.getOrderItems().remove(orderItem);
        } else if (!orderItem.isConfirmed()) {
            orderItem.getItem().setStockLevel(orderItem.getItem().getStockLevel() + 1);
            this.getOrderItems().remove(orderItem);
        } else {
            throw new IllegalCallerException("Only employees with access level MEDIUM or HIGH are allowed to remove " +
                    "confirmed items");
        }
    }

    /**
     * Checks if this order has been fully paid for. If true, closes the order and removes the link between this order
     * and the table it has been assigned to.
     */
    public void closeIfPaid() {
        if ((this.isOpen() && this.calculateTotalPaid().compareTo(this.calculateTotalCost()) > -1)) {
            this.getOrderItems()
                    .stream()
                    .filter(e -> !e.isConfirmed())
                    .map(OrderItem::getItem)
                    .forEach(e -> e.setStockLevel(e.getStockLevel() + 1));
            this.setOpen(false);
            this.getTable().setCurrentOrder(null);
        }
    }

    /**
     * Calculates the total cost of all items listed on this order.
     *
     * @return Total cost of this order.
     */
    private BigDecimal calculateTotalCost() {
        BigDecimal totalBalance = new BigDecimal(0);
        return this.getOrderItems()
                .stream()
                .filter(OrderItem::isConfirmed)
                .map(OrderItem::calculateTotalPrice)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

    /**
     * Calculates the sum of all the payments taken against this order.
     *
     * @return The sum of all payments already taken against this order.
     */
    private BigDecimal calculateTotalPaid() {
        return this.getOwner().getPayments().stream().filter(e -> e.getOrder() == this)
                .map(Payment::getAmount)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

    // getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getNumberOfcovers() {
        return numberOfcovers;
    }

    public void setNumberOfcovers(int numberOfcovers) {
        this.numberOfcovers = numberOfcovers;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public WaiterShift getOwner() {
        return owner;
    }

    public void setOwner(WaiterShift owner) {
        this.owner = owner;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
