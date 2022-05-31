package pl.fidiym.contacts.logic;

import java.time.LocalDate;

public class PersonContact extends Contact {
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;

    public PersonContact(String number, String firstName, String lastName, String gender, LocalDate birthDate) {
        super(number);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    //
    // GETTERS & SETTERS
    // ---------------------------------------------------------------------------------------------------------
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate != null ? birthDate.toString() : "[no data]";
    }

    public String getGender() {
        return gender != null ? gender : "[no data]";
    }

    @Override
    public String get_name() {
        return firstName + " " + lastName;
    }

    @Override
    public String getInfoFXML() {
        return "/pl/fidiym/contacts/otherscreens/person-addcontact-view.fxml";
    }
}