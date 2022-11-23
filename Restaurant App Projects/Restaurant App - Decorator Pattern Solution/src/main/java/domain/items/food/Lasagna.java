package domain.items.food;

import java.math.BigDecimal;

/**
 * A non-abstract representation of a lasagna food item.
 *
 * @author Adam Will
 */
public class Lasagna extends FoodItem {

    // variables
    private static int stockLevel;

    // constructors
    public Lasagna() {
        super(new BigDecimal("11.5"), 1100, true, false, false, "Lasagna");
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        Lasagna.stockLevel = stockLevel;
    }
}