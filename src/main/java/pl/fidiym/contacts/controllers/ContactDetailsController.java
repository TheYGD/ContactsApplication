package pl.fidiym.contacts.controllers;

import pl.fidiym.contacts.guicomponents.ContactCard;
import pl.fidiym.contacts.logic.Contact;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactDetailsController implements SceneController {
    @FXML
    private Pane contactDetailsAnchorPane;

    private ContactInfoController conctactInfoController;
    private ContactsController contactsControllerontroller;
    private ContactCard contactCard;


    public void init(ContactsController contactsControllerontroller) {
        this.contactsControllerontroller = contactsControllerontroller;
        contactDetailsAnchorPane.getScene().getStylesheets().add(getClass().getResource("/pl/fidiym/styles/addcontact-style.css").toExternalForm());

        contactDetailsAnchorPane.getScene().getWindow().setOnCloseRequest(event -> {
            contactsControllerontroller.setAnotherScreenInactive();
        });

        fillContactInfoPane();
    }

    public void setContactCard(ContactCard card) {
        this.contactCard = card;
    }

    public void back_to_contacts() {
        contactsControllerontroller.setAnotherScreenInactive();
        contactDetailsAnchorPane.getScene().getWindow().hide();
    }

    public void apply_changes() {
        Contact editedContact = conctactInfoController.add_contact();
        if (editedContact != null) {
            this.remove_contact();
            contactsControllerontroller.add_contact(editedContact);
        }
    }

    public void remove_contact() {
        contactsControllerontroller.remove_contact(contactCard);

        contactsControllerontroller.setAnotherScreenInactive();
        contactDetailsAnchorPane.getScene().getWindow().hide();
    }

    private void fillContactInfoPane() {
        // picture
        ((ImageView)contactDetailsAnchorPane.getChildren().get(0)).setImage(contactCard.getPicture());

        // contact type
        Matcher matcher = Pattern.compile("[A-Z][a-z]+").matcher( contactCard.getContact().getClass().getName());
        matcher.find();
        String contactType = matcher.group();
        ((Label)contactDetailsAnchorPane.getChildren().get(6)).setText(contactType);

        //info pane
        FXMLLoader loader = new FXMLLoader(getClass().getResource(contactCard.getContact().getInfoFXML()));
        AnchorPane contactInfoPane = null;
        try {
            contactInfoPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        contactInfoPane.setLayoutX(230);
        contactInfoPane.setLayoutY(30);

        conctactInfoController = loader.getController();
        conctactInfoController.init();
        conctactInfoController.fill_fields(contactCard.getContact());
        contactDetailsAnchorPane.getChildren().set(1, contactInfoPane);
    }
}
