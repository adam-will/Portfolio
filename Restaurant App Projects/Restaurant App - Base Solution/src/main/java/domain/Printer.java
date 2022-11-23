package domain;

import domain.items.*;
import domain.items.beverages.BeverageExtra;
import domain.items.foods.FoodExtra;
import domain.orders.OrderItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

/**
 * A utility class used to format the data about incoming orders into human-readable messages. The messages are passed
 * to the bar and kitchen areas where the orders are being processed.
 */
public class Printer {

    /**
     * Prints the order details. Only includes order items that have not been previously sent for processing. Separates
     * the order items into two sections, one for the bar staff and one for the kitchen staff.
     *
     * @param orderItems Order items listed against the order.
     * @param table      Table assigned against the order.
     */
    public static void printOrder(ArrayList<OrderItem> orderItems, Table table) {
        // Set the date time format
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Extract items
        ArrayList<Item> foodItems =
                orderItems.stream().map(OrderItem::getItem).filter(item -> item.getType() == ItemType.FOOD).collect(toCollection(ArrayList::new));
        ArrayList<Item> beverageItems =
                orderItems.stream().map(OrderItem::getItem).filter(item -> item.getType() == ItemType.BEVERAGE).collect(toCollection(ArrayList::new));

        // Print message
        System.out.println();
        System.out.println("Time: " + LocalDateTime.now().format(dateTimeFormatter));
        System.out.println("Table: " + table.getTableNumber());
        System.out.println("============================");
        System.out.println("Bar Area:");
        for (Item item : beverageItems) {
            System.out.println(item.getClass().getSimpleName());
            for (BeverageExtra beverageExtra : item.getBeverageExtras().keySet()) {
                System.out.println("    * " + beverageExtra.getName() + " x " + item.getBeverageExtras().get(beverageExtra));
            }
        }
        System.out.println();
        System.out.println("Kitchen Area:");
        for (Item item : foodItems) {
            System.out.println(item.getClass().getSimpleName());
            for (FoodExtra foodExtra : item.getFoodExtras().keySet()) {
                System.out.println("    * " + foodExtra.getName() + " x " + item.getFoodExtras().get(foodExtra));
            }
        }
        System.out.println("============================");
        System.out.println();
    }
}
