package domain.items;

import domain.items.beverages.BeverageExtra;
import domain.items.foods.FoodExtra;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.util.EnumMap;

/**
 * An item offered by a restaurant. Records attributes and behaviour shared by all items regardless of their
 * type.
 *
 * @author Adam Will
 */
public abstract class Item {

    // variables
    final private BigDecimal basePrice;
    final private int baseNumberOfCalories;
    final private boolean baseContainsAllergen;

    // constructors
    protected Item(BigDecimal basePrice, int baseNumberOfCalories, boolean baseContainsAllergen) {
        this.basePrice = basePrice;
        this.baseNumberOfCalories = baseNumberOfCalories;
        this.baseContainsAllergen = baseContainsAllergen;
    }

    // methods

    /**
     * Adds the provided additional food type ingredient to this item.
     *
     * @param foodExtra Additional ingredient to be added to this item
     * @throws OperationNotSupportedException Thrown when this method is called within the child class of beverage type.
     */
    public abstract void addFoodExtra(FoodExtra foodExtra) throws OperationNotSupportedException;

    /**
     * Adds the provided additional beverage type ingredient to this item.
     *
     * @param beverageExtra Additional ingredient to be added to this item
     * @throws OperationNotSupportedException Thrown when this method is called within the child class of food type.
     */
    public abstract void addBeverageExtra(BeverageExtra beverageExtra) throws OperationNotSupportedException;

    // getters and setters
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public int getBaseNumberOfCalories() {
        return baseNumberOfCalories;
    }

    public boolean isBaseContainsAllergen() {
        return baseContainsAllergen;
    }

    public abstract int getStockLevel();

    public abstract void setStockLevel(int stockLevel);

    public abstract ItemType getType();

    public abstract EnumMap<FoodExtra, Integer> getFoodExtras();

    public abstract EnumMap<BeverageExtra, Integer> getBeverageExtras();
}
