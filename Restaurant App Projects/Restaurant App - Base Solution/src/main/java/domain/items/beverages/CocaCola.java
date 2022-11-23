package domain.items.beverages;

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
        super(new BigDecimal(2.0), 300, false, false);
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        CocaCola.stockLevel = stockLevel;
    }
}