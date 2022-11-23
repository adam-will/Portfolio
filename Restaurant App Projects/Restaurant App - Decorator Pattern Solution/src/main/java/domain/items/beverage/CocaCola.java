package domain.items.beverage;

import java.math.BigDecimal;

/**
 * A non-abstract representation of a Coca-Cola beverage item.
 *
 * @author Adam Will
 */
public class CocaCola extends BeverageItem {

    // variables
    private static int stockLevel;

    // constructors
    public CocaCola() {
        super(new BigDecimal("2.5"), 300, false, false, "Coca-Cola");
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        CocaCola.stockLevel = stockLevel;
    }
}
