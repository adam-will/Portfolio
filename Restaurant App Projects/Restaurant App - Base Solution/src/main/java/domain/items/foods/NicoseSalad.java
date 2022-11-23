package domain.items.foods;

import java.math.BigDecimal;

/**
 * A non-abstract representation of a nicose salad food item.
 *
 * @author Adam Will
 */
public class NicoseSalad extends FoodItem {

    // variables
    private static int stockLevel;

    // constructors
    public NicoseSalad() {
        super(new BigDecimal("11.5"), 500, true, true, false);
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        NicoseSalad.stockLevel = stockLevel;
    }
}