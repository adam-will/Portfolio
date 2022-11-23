package domain.items.food;

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
        super(new BigDecimal("13.5"), 1050, true, false, false, "Calzone");
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        Calzone.stockLevel = stockLevel;
    }
}