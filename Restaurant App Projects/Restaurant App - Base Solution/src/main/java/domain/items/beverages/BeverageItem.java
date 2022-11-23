package domain.items.beverages;


import domain.items.Item;
import domain.items.ItemType;
import domain.items.foods.FoodExtra;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.util.EnumMap;

/**
 * An item of type beverage. Records attributes and behaviours specific to beverages.
 *
 * @author Adam Will
 */

public abstract class BeverageItem extends Item {

    // variables
    private final ItemType TYPE = ItemType.BEVERAGE;
    private boolean baseAgeRestricted;
    private EnumMap<BeverageExtra, Integer> beverageExtras;

    // constructors
    protected BeverageItem(BigDecimal basePrice, int baseNumberOfCalories,
                           boolean baseContainsAllergen, boolean baseAgeRestricted) {
        super(basePrice, baseNumberOfCalories, baseContainsAllergen);
        this.beverageExtras = new EnumMap<BeverageExtra, Integer>(BeverageExtra.class);
        this.baseAgeRestricted = baseAgeRestricted;
    }

    // methods

    /**
     * Adds the provided additional beverage type ingredient to this item.
     *
     * @param beverageExtra Additional ingredient to be added to this item
     */
    public void addBeverageExtra(BeverageExtra beverageExtra) {
        if (this.beverageExtras.containsKey(beverageExtra)) {
            this.beverageExtras.put(beverageExtra, this.beverageExtras.get(beverageExtra) + 1);
        } else {
            this.beverageExtras.put(beverageExtra, 1);
        }
    }

    /**
     * Method not available within this class. Throws OperationNotSupportedException when invoked. To be revised.
     *
     * @param foodExtra Additional ingredient to be added to this item.
     * @throws OperationNotSupportedException Thrown when this method is invoked.
     */
    public void addFoodExtra(FoodExtra foodExtra) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    // getters and setters
    public boolean isBaseAgeRestricted() {
        return baseAgeRestricted;
    }

    public void setBaseAgeRestricted(boolean baseAgeRestricted) {
        this.baseAgeRestricted = baseAgeRestricted;
    }

    public EnumMap<BeverageExtra, Integer> getBeverageExtras() {
        return beverageExtras;
    }

    public EnumMap<FoodExtra, Integer> getFoodExtras() {
        return null;
    }

    public ItemType getType() {
        return this.TYPE;
    }
}
