package domain.items.beverage;

import java.math.BigDecimal;

/**
 * A non-abstract part of the Decorator Pattern implementation. Can be wrapped around the non-abstract classes that
 * extend the BeverageItem class. Represents an additional ingredient - whiskey.
 *
 * @author Adam Will
 */
public class WhiskeyDecorator extends BeverageItemDecorator {

    // constructors
    public WhiskeyDecorator(BeverageItem beverageComponent) {
        super(new BigDecimal("2.50"), 120, false, true, beverageComponent, "Whiskey");
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
