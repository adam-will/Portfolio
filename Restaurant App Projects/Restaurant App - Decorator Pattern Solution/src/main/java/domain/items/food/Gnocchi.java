package domain.items.food;

import java.math.BigDecimal;

/**
 * A non-abstract representation of a gnocchi food item.
 *
 * @author Adam Will
 */
public class Gnocchi extends FoodItem {

    // variables
    private static int stockLevel;

    // constructors
    public Gnocchi() {
        super(new BigDecimal("12"), 900, false, true, false, "Gnocchi");
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        Gnocchi.stockLevel = stockLevel;
    }

}
