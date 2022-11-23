package domain.items.foods;

import domain.items.beverages.BeverageExtra;
import domain.items.Item;
import domain.items.ItemType;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.util.EnumMap;

/**
 * An item of type food. Records attributes and behaviours specific to food items.
 *
 * @author Adam Will
 */
public abstract class FoodItem extends Item {

    // variables
    final private boolean baseVegan;
    final private boolean baseVegetarian;
    private final ItemType TYPE = ItemType.FOOD;
    private EnumMap<FoodExtra, Integer> foodExtras;

    // constructors
    protected FoodItem(BigDecimal basePrice, int baseNumberOfCalories, boolean baseContainsAllergen,
                       boolean baseVegetarian, boolean baseVegan) {
        super(basePrice, baseNumberOfCalories, baseContainsAllergen);
        this.foodExtras = new EnumMap<FoodExtra, Integer>(FoodExtra.class);
        this.baseVegan = baseVegan;
        this.baseVegetarian = baseVegetarian;
    }

    // methods

    /**
     * Adds the provided additional food type ingredient to this item.
     *
     * @param foodExtra Additional ingredient to be added to this item
     */
    public void addFoodExtra(FoodExtra foodExtra) {
        if (this.foodExtras.containsKey(foodExtra)) {
            this.foodExtras.put(foodExtra, this.foodExtras.get(foodExtra) + 1);
        } else {
            this.foodExtras.put(foodExtra, 1);
        }
    }

    /**
     * Method not available within this class. Throws OperationNotSupportedException when invoked. To be revised.
     *
     * @param beverageExtra Additional ingredient to be added to this item.
     * @throws OperationNotSupportedException Thrown when this method is invoked.
     */
    public void addBeverageExtra(BeverageExtra beverageExtra) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    // getters and setters
    public boolean isBaseVegan() {
        return baseVegan;
    }

    public boolean isBaseVegetarian() {
        return baseVegetarian;
    }

    public EnumMap<FoodExtra, Integer> getFoodExtras() {
        return foodExtras;
    }

    public void setFoodExtras(EnumMap<FoodExtra, Integer> foodExtras) {
        this.foodExtras = foodExtras;
    }

    public EnumMap<BeverageExtra, Integer> getBeverageExtras() {
        return null;
    }

    public ItemType getType() {
        return this.TYPE;
    }
}
