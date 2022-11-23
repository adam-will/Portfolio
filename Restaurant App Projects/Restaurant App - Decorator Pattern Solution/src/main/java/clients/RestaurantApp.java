package clients;

import domain.*;
import domain.employees.Employee;
import domain.employees.WaiterShift;
import domain.items.*;
import domain.items.beverage.BeverageItem;
import domain.items.food.FoodItem;
import domain.orders.Order;
import domain.orders.OrderItem;
import domain.orders.PaymentType;

import javax.management.InvalidAttributeValueException;
import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

/**
 * The coordinating class for the Restaurant App core system. List static methods corresponding to the identified use
 * cases. Contains the main method.
 */
public class RestaurantApp {

    public static void main(String[] args) {
        // TODO: Catch all exceptions so main does not need to throw anything?
    }

    /**
     * Opens a new order and assigns it to the selected table and waiter shift.
     *
     * @param table          Table the new order will be assigned to.
     * @param waiterShift    Waiter shift the new order will be assigned to.
     * @param numberOfCovers Number of covers assigned to the new order. Used for reporting.
     * @return New object of the Order class.
     * @throws IllegalStateException Thrown when the selected waiter shift is inactive or the selected table is already
     *                               occupied.
     */

    public static Order openOrder(Table table, WaiterShift waiterShift, int numberOfCovers) throws IllegalStateException {
        if (!waiterShift.isActive())
            throw new IllegalStateException("Cannot link new order to an inactive waiter shift");
        if (!table.checkTableAvailability())
            throw new IllegalStateException("Cannot link new order to an already occupied table");
        return waiterShift.addNewOrder(table, numberOfCovers);
    }

    /**
     * Creates a new order item and assigns it to the selected order.
     *
     * @param order Order the new order item will be assigned to.
     * @param item  Item the new order item will be based on.
     * @return New object of the OrderItem class.
     * @throws IllegalStateException Thrown when the selected order is closed or the item stock level counter is lower
     *                               than or equal to zero.
     */
    public static OrderItem addItemToOrder(Order order, Item item) throws IllegalStateException {
        if (!order.isOpen()) throw new IllegalStateException("Cannot add new item to a closed order");
        if (item.getStockLevel() <= 0)
            throw new IllegalStateException("Cannot add new item if its stock level is below 0");
        return order.addItemToOrder(item);
    }

    /**
     * Removes the selected order item from the selected order.
     *
     * @param employee  User who requested for the order item to be removed.
     * @param order     Order the selected order item is listed against.
     * @param orderItem Order item to be removed.
     * @throws IllegalStateException    Thrown when the selected order is closed.
     * @throws IllegalArgumentException Thrown when the selected order item is not linked to the selected order.
     */
    public static void removeItemFromOrder(Employee employee, Order order, OrderItem orderItem) throws IllegalStateException, IllegalArgumentException {
        if (!order.isOpen()) throw new IllegalStateException("Cannot remove products from a closed order");
        if (!order.getOrderItems().contains(orderItem))
            throw new IllegalArgumentException("Selected order does not list the order item");

        try {
            order.removeItemFromOrder(employee, orderItem);
        } catch (IllegalCallerException e) {
            System.out.println(e);
        }
    }

    /**
     * Adds an additional ingredient of type beverage to the selected order item.
     *
     * @param orderItem     Order item the additional ingredient will be recorded against.
     * @param beverageExtra The additional ingredient to be added.
     * @throws OperationNotSupportedException Thrown when the selected order item has already been confirmed and sent
     *                                        for processing, or the selected order item is linked to an item of
     *                                        type food.
     */
    public static void addBeverageExtra(OrderItem orderItem, BeverageDecoratorType beverageExtra) throws OperationNotSupportedException {
        if (orderItem.isConfirmed()) {
            throw new OperationNotSupportedException("Cannot add extras to an already confirmed item");
        } else if (orderItem.getItem().getType() != ItemType.BEVERAGE) {
            throw new OperationNotSupportedException("Cannot add beverage ingredients to an item of type: " + orderItem.getItem().getType());
        } else {
            IngredientDecoratorFactory ingredientDecoratorFactory =
                    IngredientDecoratorFactory.getIngredientDecoratorFactory();
            try {

                orderItem.setItem(ingredientDecoratorFactory.addBeverageDecorator((BeverageItem) orderItem.getItem(),
                        beverageExtra));
            } catch (InvalidAttributeValueException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Adds an additional ingredient of type food to the selected order item.
     *
     * @param orderItem Order item the additional ingredient will be recorded against.
     * @param foodExtra The additional ingredient to be added.
     * @throws OperationNotSupportedException Thrown when the selected order item has already been confirmed and sent
     *                                        for processing, or the selected order item is linked to an item of
     *                                        type beverage.
     */
    public static void addFoodExtra(OrderItem orderItem, FoodDecoratorType foodExtra) throws OperationNotSupportedException {
        if (orderItem.isConfirmed()) {
            throw new OperationNotSupportedException("Cannot add extras to an already confirmed item");
        } else if (orderItem.getItem().getType() != ItemType.FOOD) {
            throw new OperationNotSupportedException("Cannot add food ingredients to an item of type: " + orderItem.getItem().getType());
        } else {
            IngredientDecoratorFactory ingredientDecoratorFactory =
                    IngredientDecoratorFactory.getIngredientDecoratorFactory();
            try {
                orderItem.setItem(ingredientDecoratorFactory.addFoodDecorator((FoodItem) orderItem.getItem(),
                        foodExtra));
            } catch (InvalidAttributeValueException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Sends any currently unconfirmed order items for processing. Marks the order its unconfirmed order items as
     * confirmed.
     *
     * @param order Order to be confirmed.
     */
    public static void confirmOrder(Order order) {
        ArrayList<OrderItem> unconfirmedOrderItems =
                order.getOrderItems().stream().filter(e -> !e.isConfirmed()).collect(toCollection(ArrayList::new));
        unconfirmedOrderItems.forEach(e -> e.setConfirmed(true));
        Printer.printOrder(unconfirmedOrderItems, order.getTable());
    }

    /**
     * Records a new payment.
     *
     * @param order       Order the new payment will be recorder against.
     * @param waiterShift Waiter shift the new payment will be recorder against.
     * @param paymentType Type of the payment.
     * @param amount      Amount taken.
     */
    public static void takePayment(Order order, WaiterShift waiterShift, PaymentType paymentType, BigDecimal amount) {
        try {
            waiterShift.takePayment(order, paymentType, amount);
        } catch (IllegalStateException | InvalidAttributeValueException e) {
            System.out.println(e);
        }
    }

}
