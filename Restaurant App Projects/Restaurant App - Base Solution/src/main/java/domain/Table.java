package domain;

import domain.orders.Order;

/**
 * A table in a restaurant.
 *
 * @author Adam Will
 */
public class Table {

    // variables
    private Order currentOrder;
    private int tableNumber;

    // constructors
    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    // methods

    /**
     * Checks whether this table is currently assigned to any of the orders.
     *
     * @return A boolean value showing whether this table is currently available.
     */
    public boolean checkTableAvailability() {
        return currentOrder == null;
    }

    // getters and setters
    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
