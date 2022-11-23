package domain.items.food;

import java.math.BigDecimal;

/**
 * A part of the Decorator Pattern implementation. This class both extends and references the FoodItem class, and
 * therefore allows for wrapping food decorator objects around it.
 *
 * @author Adam Will
 */
public abstract class FoodItemDecorator extends FoodItem {

    // variables
    private final FoodItem foodComponent;

    // constructors
    public FoodItemDecorator(BigDecimal price, int numberOfCalories, boolean containsAllergen, boolean isVegetarian,
                             boolean isVegan, String name, FoodItem foodComponent) {
        super(price, numberOfCalories, containsAllergen, isVegetarian, isVegan, name);
        this.foodComponent = foodComponent;
    }

    // getters and setters
    @Override
    public BigDecimal getPrice() {
        return this.getIngredientPrice().add(foodComponent.getPrice());
    }

    private BigDecimal getIngredientPrice() {
        return super.getPrice();
    }

    @Override
    public int getNumberOfCalories() {
        return this.getIngredientNumberOfCalories() + foodComponent.getNumberOfCalories();
    }

    private int getIngredientNumberOfCalories() {
        return super.getNumberOfCalories();
    }

    @Override
    public boolean isContainsAllergen() {
        return this.isIngredientContainsAllergen() || foodComponent.isContainsAllergen();
    }

    private boolean isIngredientContainsAllergen() {
        return super.isContainsAllergen();
    }

    @Override
    public String getName() {
        return foodComponent.getName() + "\n    * " + super.getName();
    }

    @Override
    public boolean isVegan() {
        return super.isVegan() || foodComponent.isVegan();
    }

    @Override
    public boolean isVegetarian() {
        return super.isVegetarian() || foodComponent.isVegetarian();
    }

    public FoodItem getFoodComponent() {
        return this.foodComponent;
    }
}
