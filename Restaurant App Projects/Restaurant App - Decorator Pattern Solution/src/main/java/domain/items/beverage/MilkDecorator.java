package domain.items.beverage;

import java.math.BigDecimal;

/**
 * A non-abstract part of the Decorator Pattern implementation. Can be wrapped around the non-abstract classes that
 * extend the BeverageItem class. Represents an additional ingredient - milk.
 *
 * @author Adam Will
 */
public class MilkDecorator extends BeverageItemDecorator {

    // constructors
    public MilkDecorator(BeverageItem beverageComponent) {
        super(new BigDecimal("0.25"), 70, true, false, beverageComponent, "Milk");
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
