package domain.items.beverage;

import java.math.BigDecimal;

/**
 * A non-abstract part of the Decorator Pattern implementation. Can be wrapped around the non-abstract classes that
 * extend the BeverageItem class. Represents an additional ingredient - ice.
 *
 * @author Adam Will
 */
public class IceDecorator extends BeverageItemDecorator {

    // constructors
    public IceDecorator(BeverageItem beverageComponent) {
        super(new BigDecimal("0.00"), 0, false, false, beverageComponent, "Ice");
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