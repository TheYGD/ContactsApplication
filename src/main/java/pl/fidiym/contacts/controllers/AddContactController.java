package pl.fidiym.contacts.controllers;

import pl.fidiym.contacts.logic.Contact;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;

public class AddContactController implements SceneController {
    @FXML
    private Pane bottomPane;

    private ContactInfoController conctactBuilderController;
    private ContactsController contactsControllerontroller;


    public void init(ContactsController contactsControllerontroller) {
        this.contactsControllerontroller = contactsControllerontroller;
        bottomPane.getScene().getStylesheets().add(getClass().getResource("/pl/fidiym/styles/addcontact-style.css").toExternalForm());

        bottomPane.getScene().getWindow().setOnCloseRequest(event -> {
            contactsControllerontroller.setAnotherScreenInactive();
        });
    }

    private void fillConreteContactInfoBuilder(String concreteBuilderFXML) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(concreteBuilderFXML));
        AnchorPane contactBuilderPane = loader.load();
        contactBuilderPane.setLayoutX(194);
        contactBuilderPane.setLayoutY(33);

        conctactBuilderController = loader.getController();
        conctactBuilderController.init();
        bottomPane.getChildren().set(2, contactBuilderPane);
    }

    public void selectPersonContact() throws IOException {
        fillConreteContactInfoBuilder("/pl/fidiym/contacts/otherscreens/person-addcontact-view.fxml");
    }

    public void selectOrganizationContact() throws IOException {
        fillConreteContactInfoBuilder("/pl/fidiym/contacts/otherscreens/organization-addcontact-view.fxml");
    }

    public void add_contact() {
        Contact contact = conctactBuilderController.add_contact();
        if (contact == null) {
            return;
        }

        contactsControllerontroller.add_contact(contact);
        contactsControllerontroller.setAnotherScreenInactive();
        bottomPane.getScene().getWindow().hide();
    }

}
