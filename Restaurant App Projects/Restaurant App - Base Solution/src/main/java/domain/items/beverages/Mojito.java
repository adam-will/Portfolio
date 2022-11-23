package domain.items.beverages;

import java.math.BigDecimal;

/**
 * A non-abstract representation of a mojito beverage item.
 *
 * @author Adam Will
 */
public class Mojito extends BeverageItem {

    // variables
    private static int stockLevel;

    // constructors
    public Mojito() {
        super(new BigDecimal(8.5), 320, false, true);
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        Mojito.stockLevel = stockLevel;
    }
}