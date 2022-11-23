package domain.items.beverages;

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
        super(new BigDecimal(2.5), 10, false, false);
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        Tea.stockLevel = stockLevel;
    }
}