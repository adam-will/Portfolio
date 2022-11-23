package domain.items.beverage;

import java.math.BigDecimal;

/**
 * A non-abstract part of the Decorator Pattern implementation. Can be wrapped around the non-abstract classes that
 * extend the BeverageItem class. Represents an additional ingredient - lemon.
 *
 * @author Adam Will
 */
public class LemonDecorator extends BeverageItemDecorator {

    // constructors
    public LemonDecorator(BeverageItem beverageComponent) {
        super(new BigDecimal("0.10"), 10, false, false, beverageComponent, "Lemon");
    }

    // getters and setters
    public int getStockLevel() {
        // not yet implemented - currently points to the stock level of the main dish
        return super.getBeverageComponent().getStockLevel();
    }

    public void setStockLevel(int stockLevel) {
        // not yet implemented - currently points to the stock level of the main dish
        super.getBeverageComponent().setStockLevel(stockLevel);
    }
}