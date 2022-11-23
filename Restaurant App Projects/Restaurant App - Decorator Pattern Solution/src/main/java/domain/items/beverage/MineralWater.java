package domain.items.beverage;

import java.math.BigDecimal;

/**
 * A non-abstract representation of a mineral water beverage item.
 *
 * @author Adam Will
 */
public class MineralWater extends BeverageItem {

    // variables
    private static int stockLevel;

    // constructors
    public MineralWater() {
        super(new BigDecimal("2.5"), 5, false, false, "Mineral Water");
    }

    // getters and setters
    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        MineralWater.stockLevel = stockLevel;
    }
}