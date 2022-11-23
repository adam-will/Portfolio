package domain.employees;

import java.time.LocalDate;

/**
 * An employee working in a restaurant.
 *
 * @author Adam Will
 */
public abstract class Employee {

    // variables
    private long id;
    private String firstName;
    private String surname;
    private LocalDate dateOfBirth;
    private AccessLevel accessLevel;

    // constructors
    public Employee(String firstName, String surname, LocalDate dateOfBirth, AccessLevel accessLevel) {
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.accessLevel = accessLevel;
    }

    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}
