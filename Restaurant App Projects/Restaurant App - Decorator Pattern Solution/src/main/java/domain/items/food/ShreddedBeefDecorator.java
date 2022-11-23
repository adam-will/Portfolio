package domain.items.food;

import java.math.BigDecimal;

/**
 * A non-abstract part of the Decorator Pattern implementation. Can be wrapped around the non-abstract classes that
 * extend the FoodItem class. Represents an additional ingredient - shredded beef.
 *
 * @author Adam Will
 */
public class ShreddedBeefDecorator extends FoodItemDecorator {

    // constructors
    public ShreddedBeefDecorator(FoodItem foodComponent) {
        super(new BigDecimal("2.50"), 140, false, false, false, "Shredded Beef", foodComponent);
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