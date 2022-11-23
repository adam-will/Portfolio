package domain.items.foods;

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
        super(new BigDecimal(10), 850, true, false, false);
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        Lasagna.stockLevel = stockLevel;
    }
}