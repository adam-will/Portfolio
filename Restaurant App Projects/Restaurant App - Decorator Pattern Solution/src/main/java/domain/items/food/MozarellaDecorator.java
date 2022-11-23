package domain.items.food;

import java.math.BigDecimal;

/**
 * A non-abstract part of the Decorator Pattern implementation. Can be wrapped around the non-abstract classes that
 * extend the FoodItem class. Represents an additional ingredient - mozarella.
 *
 * @author Adam Will
 */
public class MozarellaDecorator extends FoodItemDecorator {

    // constructors
    public MozarellaDecorator(FoodItem foodComponent) {
        super(new BigDecimal("1.50"), 110, true, true, false, "Mozzarella", foodComponent);
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