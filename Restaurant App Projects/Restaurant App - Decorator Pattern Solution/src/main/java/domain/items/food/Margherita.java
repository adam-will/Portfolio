package domain.items.food;

import java.math.BigDecimal;

/**
 * A non-abstract representation of a margherita food item.
 *
 * @author Adam Will
 */
public class Margherita extends FoodItem {

    // variables
    private static int stockLevel;

    // constructors
    public Margherita() {
        super(new BigDecimal("10.5"), 750, true, true, false, "Margherita");
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        Margherita.stockLevel = stockLevel;
    }
}