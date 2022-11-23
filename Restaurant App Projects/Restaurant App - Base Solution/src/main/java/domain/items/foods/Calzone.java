package domain.items.foods;

import java.math.BigDecimal;

/**
 * A non-abstract representation of a calzone food item.
 *
 * @author Adam Will
 */
public class Calzone extends FoodItem {

    // variables
    private static int stockLevel;

    // constructors
    public Calzone() {
        super(new BigDecimal("14.5"), 950, false, false, false);
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        Calzone.stockLevel = stockLevel;
    }
}