package domain.items;

import java.math.BigDecimal;

/**
 * An item offered by a restaurant. Records attributes and behaviour shared by all items regardless of their
 * type.
 *
 * @author Adam Will
 */
public abstract class Item {

    // variables
    private final BigDecimal price;
    private final int numberOfCalories;
    private final boolean containsAllergens;
    private final String name;

    // constructors
    protected Item(BigDecimal price, int numberOfCalories, boolean containsAllergens, String name) {
        this.price = price;
        this.numberOfCalories = numberOfCalories;
        this.containsAllergens = containsAllergens;
        this.name = name;
    }

    // getters and setters
    public BigDecimal getPrice() {
        return this.price;
    }

    public int getNumberOfCalories() {
        return this.numberOfCalories;
    }

    public boolean isContainsAllergen() {
        return this.containsAllergens;
    }

    public String getName() {
        return name;
    }

    public abstract int getStockLevel();

    public abstract void setStockLevel(int stockLevel);

    public abstract ItemType getType();
}
