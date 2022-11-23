package domain.items.beverage;

import java.math.BigDecimal;

/**
 * A non-abstract representation of a tea beverage item.
 *
 * @author Adam Will
 */
public class Tea extends BeverageItem {

    // variables
    private static int stockLevel;

    // constructors
    public Tea() {
        super(new BigDecimal("2.0"), 30, false, false, "Tea");
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        Tea.stockLevel = stockLevel;
    }
}