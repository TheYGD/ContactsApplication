package pl.fidiym.contacts.controllers;

import pl.fidiym.contacts.logic.Contact;
import pl.fidiym.contacts.logic.OrganizationContact;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class OrganizationContactInfoController implements ContactInfoController {
    @FXML
    private TextField nameTf;
    @FXML
    private TextField numberTf;
    @FXML
    private TextField addressTf;


    @Override
    public void init() {
        nameTf.focusedProperty().addListener((obs, oldVal, focused) -> {
            if (focused) {
                nameTf.getStyleClass().removeAll("text-input-error");
            }
        });
        numberTf.focusedProperty().addListener((obs, oldVal, focused) -> {
            if (focused) {
                numberTf.getStyleClass().removeAll("text-input-error");
            }
        });
        addressTf.focusedProperty().addListener((obs, oldVal, focused) -> {
            if (focused) {
                addressTf.getStyleClass().removeAll("text-input-error");
            }
        });
    }

    public boolean all_fields_valid() {
        boolean allValid = true;

        if (nameTf.getText().matches("\\s*")) {
            nameTf.getStyleClass().add("text-input-error");
            allValid = false;
        }
        if (addressTf.getText().matches("\\s*")) {
            addressTf.getStyleClass().add("text-input-error");
            allValid = false;
        }
        if (!Contact.is_right_number_format(numberTf.getText())) {
            numberTf.getStyleClass().add("text-input-error");
            allValid = false;
        }

        return allValid;
    }

    @Override
    public Contact add_contact() {
        if (!all_fields_valid()) {
            return null;
        }

        return new OrganizationContact(numberTf.getText(), nameTf.getText(), addressTf.getText());
    }

    @Override
    public void fill_fields(Contact contact) {
        OrganizationContact organization = (OrganizationContact) contact;

        nameTf.setText(organization.getOrganizationName());
        numberTf.setText(organization.getNumber());
        addressTf.setText(organization.getAddress());
    }
}
