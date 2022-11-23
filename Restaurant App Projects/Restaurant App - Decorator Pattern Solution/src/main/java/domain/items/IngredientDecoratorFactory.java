package domain.items;

import domain.items.beverage.*;
import domain.items.food.*;

import javax.management.InvalidAttributeValueException;

/**
 * A factory class used to streamline the creation of decorator objects. Implements the Factory and Singleton patterns.
 *
 * @author Adam Will
 */
public final class IngredientDecoratorFactory {

    // variables
    private static IngredientDecoratorFactory INSTANCE;

    // constructors
    private IngredientDecoratorFactory() {
    }

    // methods

    /**
     * Ensures only on instance of the IngredientDecoratorFactory is active at any time.
     *
     * @return Active instance of the IngredientDecoratorFactory class
     */
    public static IngredientDecoratorFactory getIngredientDecoratorFactory() {
        if (INSTANCE == null) {
            INSTANCE = new IngredientDecoratorFactory();
        }
        return INSTANCE;
    }

    /**
     * Adds the selected additional ingredient to the selected food item.
     *
     * @param foodItem          Food item to be decorated
     * @param foodDecoratorType Type of additional ingredient to be added to the food item
     * @return Decorated object of the FoodItem class
     * @throws InvalidAttributeValueException Thrown when the provided food decorator is not supported.
     */
    public FoodItem addFoodDecorator(FoodItem foodItem, FoodDecoratorType foodDecoratorType) throws InvalidAttributeValueException {
        switch (foodDecoratorType) {
            case CHICKEN -> foodItem = new ChickenDecorator(foodItem);
            case HAM -> foodItem = new HamDecorator(foodItem);
            case JALAPENOES -> foodItem = new JalapenoesDecorator(foodItem);
            case MOZARELLA -> foodItem = new MozarellaDecorator(foodItem);
            case SHREDDEDBEEF -> foodItem = new ShreddedBeefDecorator(foodItem);
            default -> throw new InvalidAttributeValueException("Selected food ingredient is not supported");
        }
        return foodItem;
    }

    /**
     * Adds the selected additional ingredient to the selected beverage item.
     *
     * @param beverageItem          Food item to be decorated
     * @param beverageDecoratorType Type of additional ingredient to be added to the beverage item
     * @return Decorated object of the BeverageItem class
     * @throws InvalidAttributeValueException Thrown when the provided food decorator is not supported.
     */
    public Item addBeverageDecorator(BeverageItem beverageItem, BeverageDecoratorType beverageDecoratorType) throws InvalidAttributeValueException {
        switch (beverageDecoratorType) {
            case ICE -> beverageItem = new IceDecorator(beverageItem);
            case MILK -> beverageItem = new MilkDecorator(beverageItem);
            case LEMON -> beverageItem = new LemonDecorator(beverageItem);
            case WHISKEY -> beverageItem = new WhiskeyDecorator(beverageItem);
            case STEAMEDMILK -> beverageItem = new SteamedMilkDecorator(beverageItem);
            default -> throw new InvalidAttributeValueException("Selected beverage ingredient is not supported");
        }
        return beverageItem;
    }
}
