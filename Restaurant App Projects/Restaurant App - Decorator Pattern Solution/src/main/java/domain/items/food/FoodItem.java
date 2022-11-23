package domain.items.food;

import domain.items.Item;
import domain.items.ItemType;

import java.math.BigDecimal;

/**
 * An item of type food. Records attributes and behaviours specific to food dishes.
 *
 * @author Adam Will
 */
public abstract class FoodItem extends Item {

    // variables
    protected final boolean isVegan;
    protected final boolean isVegetarian;
    private final ItemType TYPE = ItemType.FOOD;

    // constructors
    public FoodItem(BigDecimal price, int numberOfCalories, boolean containsAllergens,
                    boolean isVegetarian, boolean isVegan, String name) {
        super(price, numberOfCalories, containsAllergens, name);
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
    }

    // getters and setters
    public boolean isVegan() {
        return this.isVegan;
    }

    public boolean isVegetarian() {
        return this.isVegetarian;
    }

    public ItemType getType() {
        return this.TYPE;
    }
}
