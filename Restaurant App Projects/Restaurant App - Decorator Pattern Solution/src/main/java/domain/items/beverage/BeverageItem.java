package domain.items.beverage;

import domain.items.Item;
import domain.items.ItemType;

import java.math.BigDecimal;

/**
 * An item of type beverage. Records attributes and behaviours specific to beverages.
 *
 * @author Adam Will
 */
public abstract class BeverageItem extends Item {

    // variables
    protected final boolean ageRestricted;
    private final ItemType type = ItemType.BEVERAGE;

    // constructors
    public BeverageItem(BigDecimal price, int numberOfCalories,
                        boolean containsAllergens, boolean ageRestricted, String name) {
        super(price, numberOfCalories, containsAllergens, name);
        this.ageRestricted = ageRestricted;
    }

    // getters and setters
    public boolean isAgeRestricted() {
        return this.ageRestricted;
    }

    public ItemType getType() {
        return this.type;
    }
}
