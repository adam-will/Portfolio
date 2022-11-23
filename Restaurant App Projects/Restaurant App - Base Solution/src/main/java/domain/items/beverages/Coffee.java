package domain.items.beverages;

import java.math.BigDecimal;

/**
 * A non-abstract representation of a coffee beverage item.
 *
 * @author Adam Will
 */
public class Coffee extends BeverageItem {

    // variables
    private static int stockLevel;

    // constructors
    public Coffee() {
        super(new BigDecimal(2.5), 20, false, false);
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        Coffee.stockLevel = stockLevel;
    }
}