package domain.items.food;

import java.math.BigDecimal;

/**
 * A non-abstract part of the Decorator Pattern implementation. Can be wrapped around the non-abstract classes that
 * extend the FoodItem class. Represents an additional ingredient - ham.
 *
 * @author Adam Will
 */
public class HamDecorator extends FoodItemDecorator {

    // constructors
    public HamDecorator(FoodItem foodComponent) {
        super(new BigDecimal("1.75"), 90, false, false, false, "Ham", foodComponent);
    }

    // getters and setters
    public int getStockLevel() {
        // not yet implemented - currently points to the stock level of the main dish
        return super.getFoodComponent().getStockLevel();
    }

    public void setStockLevel(int stockLevel) {
        // not yet implemented - currently points to the stock level of the main dish
        super.getFoodComponent().setStockLevel(stockLevel);
    }
}
