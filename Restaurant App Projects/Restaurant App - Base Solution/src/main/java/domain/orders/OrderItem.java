package domain.orders;

import domain.items.beverages.BeverageExtra;
import domain.items.foods.FoodExtra;
import domain.items.Item;
import domain.items.ItemType;

import java.math.BigDecimal;
import java.util.EnumMap;

/**
 * An item stored against an order. Allows for the many-to-many relationship between the Order class and the Item class.
 *
 * @author Adam Will
 */
public class OrderItem {

    // variables
    private boolean confirmed;
    private Item item;
    private Order order;

    // constructors
    public OrderItem(Order order, Item item) {
        this.item = item;
        this.order = order;
        this.confirmed = false;
        this.item.setStockLevel(this.item.getStockLevel() - 1);
    }

    // methods

    /**
     * Calculates the total price of this order item. Includes the prices of all of its additional ingredients.
     *
     * @return The total price of this order item, including additional ingredients
     */
    public BigDecimal calculateTotalPrice() {
        BigDecimal totalPrice = this.getItem().getBasePrice();
        if (this.getItem().getType() == ItemType.FOOD) {
            EnumMap<FoodExtra, Integer> foodExtras = this.getItem().getFoodExtras();
            for (FoodExtra foodExtra : foodExtras.keySet()) {
                BigDecimal foodExtraPrice = foodExtra.getPrice()
                        .multiply(BigDecimal.valueOf(foodExtras.get(foodExtra)));
                totalPrice = totalPrice.add(foodExtraPrice);
            }
        }
        if (this.getItem().getType() == ItemType.BEVERAGE) {
            EnumMap<BeverageExtra, Integer> beverageExtras = this.getItem().getBeverageExtras();
            for (BeverageExtra beverageExtra : beverageExtras.keySet()) {
                BigDecimal beverageExtraPrice = beverageExtra.getPrice()
                        .multiply(BigDecimal.valueOf(beverageExtras.get(beverageExtra)));
                totalPrice = totalPrice.add(beverageExtraPrice);
            }
        }
        return totalPrice;
    }

    // getters and setters
    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
