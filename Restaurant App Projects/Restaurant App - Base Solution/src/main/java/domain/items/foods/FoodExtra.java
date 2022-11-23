package domain.items.foods;

import java.math.BigDecimal;

/**
 * A list of food type extras. Stores information about each ingredient type, including name, age and allergy
 * restrictions, and pricing.
 *
 * @author Adam Will
 */
public enum FoodExtra {

    JALAPENOS("Jalapenoes", true, true, false, new BigDecimal("1.5")),
    MOZZARELLA("Diced Mozzarella", true, false, true, new BigDecimal("1")),
    HAM("Ham", false, false, false, new BigDecimal("1.75")),
    CHICKEN("Chicken", false, false, false, new BigDecimal("2.00")),
    BEEF("Shredded Beef", false, false, false, new BigDecimal("2.20"));

    // variables
    private String name;
    private boolean vegetarian;
    private boolean vegan;
    private boolean containsAllergens;
    private BigDecimal price;

    // constructors
    private FoodExtra(String name, boolean vegetarian, boolean vegan, boolean containsAllergens, BigDecimal price) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.containsAllergens = containsAllergens;
        this.price = price;
    }

    // getters and setters
    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public boolean isContainsAllergens() {
        return containsAllergens;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
