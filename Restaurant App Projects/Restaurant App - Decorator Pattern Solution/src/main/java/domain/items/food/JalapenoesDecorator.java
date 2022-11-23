package domain.items.food;

import java.math.BigDecimal;

/**
 * A non-abstract part of the Decorator Pattern implementation. Can be wrapped around the non-abstract classes that
 * extend the FoodItem class. Represents an additional ingredient - jalapenoes.
 *
 * @author Adam Will
 */
public class JalapenoesDecorator extends FoodItemDecorator {

    // constructors
    public JalapenoesDecorator(FoodItem foodComponent) {
        super(new BigDecimal("1.25"), 50, false, true, true, "Jalapenoes", foodComponent);
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