package domain.items.food;

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
        super(new BigDecimal("11"), 500, true, true, true, "Nicose Salad");
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        NicoseSalad.stockLevel = stockLevel;
    }
}