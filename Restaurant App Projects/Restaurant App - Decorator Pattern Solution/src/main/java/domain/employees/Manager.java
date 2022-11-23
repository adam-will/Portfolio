package domain.employees;

import java.time.LocalDate;

/**
 * An employee working as a manager.
 *
 * @author Adam Will
 */
public class Manager extends Employee {

    // constructors
    public Manager(String firstName, String surname, LocalDate dateOfBirth) {
        super(firstName, surname, dateOfBirth, AccessLevel.MEDIUM);
    }
}
