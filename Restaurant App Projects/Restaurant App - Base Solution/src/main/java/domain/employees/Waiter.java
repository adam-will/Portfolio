package domain.employees;

import java.time.LocalDate;

/**
 * An employee working as a waiter.
 *
 * @author Adam Will
 */
public class Waiter extends Employee {

    // constructors
    public Waiter(String firstName, String surname, LocalDate dateOfBirth) {
        super(firstName, surname, dateOfBirth, AccessLevel.LOW);
    }
}
