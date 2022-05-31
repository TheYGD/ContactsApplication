package pl.fidiym.contacts.controllers;

import pl.fidiym.contacts.logic.Contact;
import pl.fidiym.contacts.logic.PersonContact;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.DateTimeException;
import java.time.LocalDate;

public class PersonContactInfoController implements ContactInfoController {
    @FXML
    private TextField firstNameTf;
    @FXML
    private TextField lastNameTf;
    @FXML
    private TextField numberTf;
    @FXML
    private ChoiceBox genderChoice;
    @FXML
    private DatePicker birthDatePicker;


    @Override
    public void init() {
        genderChoice.getItems().addAll("Male", "Female", "No information");

        firstNameTf.focusedProperty().addListener((obs, oldVal, focused) -> {
            if (focused) {
                firstNameTf.getStyleClass().removeAll("text-input-error");
            }
        });
        lastNameTf.focusedProperty().addListener((obs, oldVal, focused) -> {
            if (focused) {
                lastNameTf.getStyleClass().removeAll("text-input-error");
            }
        });
        numberTf.focusedProperty().addListener((obs, oldVal, focused) -> {
            if (focused) {
                numberTf.getStyleClass().removeAll("text-input-error");
            }
        });
        genderChoice.focusedProperty().addListener((obs, oldVal, focused) -> {
            if (focused) {
                genderChoice.getStyleClass().removeAll("text-input-error");
            }
        });
        birthDatePicker.focusedProperty().addListener((obs, oldVal, focused) -> {
            if (focused) {
                birthDatePicker.getStyleClass().removeAll("text-input-error");
            }
        });
    }

    public boolean all_fields_valid() {
        boolean allValid = true;

        if (firstNameTf.getText().matches("\\s*")) {
            firstNameTf.getStyleClass().add("text-input-error");
            allValid = false;
        }
        if (lastNameTf.getText().matches("\\s*")) {
            lastNameTf.getStyleClass().add("text-input-error");
            allValid = false;
        }
        if (!Contact.is_right_number_format(numberTf.getText())) {
            numberTf.getStyleClass().add("text-input-error");
            allValid = false;
        }
        if (genderChoice.getValue() == null) {
            genderChoice.getStyleClass().add("text-input-error");
            allValid = false;
        }

        try {
            if (birthDatePicker.getValue() == null) {
                birthDatePicker.getStyleClass().add("text-input-error");
                allValid = false;
            }
        } catch (DateTimeException e) {
            allValid = false;
        }

        return allValid;
    }

    @Override
    public Contact add_contact() {
        if (!all_fields_valid()) {
            return null;
        }

        return new PersonContact(numberTf.getText(), firstNameTf.getText(), lastNameTf.getText(),
                (String) genderChoice.getValue(), birthDatePicker.getValue());
    }

    @Override
    public void fill_fields(Contact contact) {
        PersonContact person = (PersonContact) contact;

        firstNameTf.setText(person.getFirstName());
        lastNameTf.setText(person.getLastName());
        numberTf.setText(person.getNumber());
        birthDatePicker.setValue(LocalDate.parse(person.getBirthDate()));
        genderChoice.setValue(person.getGender());
    }
}
