package domain.items.beverage;

import java.math.BigDecimal;

/**
 * A part of the Decorator Pattern implementation. This class both extends and references the BeverageItem class, and
 * therefore allows for wrapping beverage decorator objects around it.
 *
 * @author Adam Will
 */
public abstract class BeverageItemDecorator extends BeverageItem {

    //variables
    private final BeverageItem beverageComponent;

    //constructors
    public BeverageItemDecorator(BigDecimal price, int numberOfCalories,
                                 boolean containsAllergens, boolean isAgeRestricted, BeverageItem beverageComponent,
                                 String name) {
        super(price, numberOfCalories, containsAllergens, isAgeRestricted, name);
        this.beverageComponent = beverageComponent;
    }

    // getters and setters
    @Override
    public BigDecimal getPrice() {
        return this.getIngredientPrice().add(beverageComponent.getPrice());
    }

    private BigDecimal getIngredientPrice() {
        return super.getPrice();
    }

    @Override
    public int getNumberOfCalories() {
        return this.getIngredientNumberOfCalories() + beverageComponent.getNumberOfCalories();
    }

    private int getIngredientNumberOfCalories() {
        return super.getNumberOfCalories();
    }

    @Override
    public boolean isContainsAllergen() {
        return this.isIngredientContainsAllergen() || beverageComponent.isContainsAllergen();
    }

    private boolean isIngredientContainsAllergen() {
        return super.isContainsAllergen();
    }

    @Override
    public String getName() {
        return beverageComponent.getName() + "\n    * " + super.getName();
    }

    @Override
    public boolean isAgeRestricted() {
        return super.isAgeRestricted() || beverageComponent.isAgeRestricted();
    }

    public BeverageItem getBeverageComponent() {
        return this.beverageComponent;
    }
}
