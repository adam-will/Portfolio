package domain.items.beverage;

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
        super(new BigDecimal("12.5"), 250, false, true, "Mojito");
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        Mojito.stockLevel = stockLevel;
    }
}