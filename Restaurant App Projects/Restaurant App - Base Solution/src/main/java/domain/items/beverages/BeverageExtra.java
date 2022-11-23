package domain.items.beverages;

import java.math.BigDecimal;

/**
 * A list of beverage type extras. Stores information about each ingredient type, including name, age and allergy
 * restrictions, and pricing.
 *
 * @author Adam Will
 */
public enum BeverageExtra {

    ICE("Ice", false, false, new BigDecimal("0")),
    STEAMEDMILK("Steamed Milk", false, true, new BigDecimal("0.5")),
    LEMON("Piece of Lemon", false, false, new BigDecimal("0.1")),
    MILK("Cold Milk", false, true, new BigDecimal("0.25")),
    WHISKEY("Jack Daniels", true, false, new BigDecimal("2"));

    // variables
    private String name;
    private boolean ageRestricted;
    private boolean containsAllergens;
    private BigDecimal price;

    // constructors
    private BeverageExtra(String name, boolean ageRestricted, boolean containsAllergens, BigDecimal price){
        this.name = name;
        this.ageRestricted = ageRestricted;
        this.containsAllergens = containsAllergens;
        this.price = price;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public boolean isAgeRestricted() {
        return ageRestricted;
    }

    public boolean isContainsAllergens() {
        return containsAllergens;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
