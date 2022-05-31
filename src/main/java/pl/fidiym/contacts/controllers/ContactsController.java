package pl.fidiym.contacts.controllers;

import pl.fidiym.contacts.guicomponents.ContactCard;
import pl.fidiym.contacts.logic.Contact;
import pl.fidiym.contacts.logic.ContactBook;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class ContactsController implements SceneController {
    @FXML
    private ImageView searchIcon;
    @FXML
    private TilePane contactsTilePane;
    @FXML
    private ImageView addContactButton;
    @FXML
    private TextField searchingTextField;

    private boolean isAnotherScreenActive;
    private ContactBook contactBook;

    public ContactsController() {
        contactBook = new ContactBook();
    }

    public void init() {
        contactsTilePane.setPrefColumns(3);
        load_contacts();

        addContactButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showAddContactScreen();
            event.consume();
        });

        searchIcon.getScene().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER && searchingTextField.isFocused()) {
                search_contacts(searchingTextField.getText());
            }
        });

        searchingTextField.focusedProperty().addListener((obs, oldVal, focused) -> searchIcon.setVisible(!focused));
        isAnotherScreenActive = false;
    }


    public void setAnotherScreenInactive() {
        isAnotherScreenActive = false;
    }

    public void add_contact(Contact contact) {
        contactBook.add_contact(contact);
        show_contacts(contactBook.get_contacts());
    }

    private void show_contacts(ArrayList<Contact> contacts) {
        ObservableList<Node> contactCards =  contactsTilePane.getChildren();
        while (contactCards.size() != 0) {
            contactCards.remove(contactCards.get(0));
        }

        contacts.sort(Comparator.comparing(x -> x.get_name().toUpperCase(Locale.ROOT)));

        for (Contact contact : contacts) {
            contactCards.add(create_contact_card(contact));
        }
    }

    public void showAddContactScreen() {
        AddContactController controller = (AddContactController) show_another_screen("/pl/fidiym/contacts/otherscreens/addcontact-view.fxml");
        if (controller == null) {
            return;
        }

        controller.init(this);
    }

    private void showContactDetailsScreen(ContactCard card) {
        ContactDetailsController controller = (ContactDetailsController) show_another_screen("/pl/fidiym/contacts/otherscreens/contactdetails-view.fxml");
        if (controller == null) {
            return;
        }

        controller.setContactCard(card);
        controller.init(this);
    }

    private SceneController show_another_screen(String sceneFXML) {
        if (isAnotherScreenActive) {
            return null;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneFXML));

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        isAnotherScreenActive = true;

        stage.show();
        return fxmlLoader.getController();
    }

    private void load_contacts() {
        show_contacts(contactBook.get_contacts());
    }
    
    private void search_contacts(String pattern) {
        ArrayList<Contact> results = contactBook.matching_contacts(pattern);
        show_contacts(results);
    }

    public void remove_contact(ContactCard card) {
        contactsTilePane.getChildren().remove(card);
        contactBook.remove(card.getContact());
    }

    public ContactCard create_contact_card(Contact contact) {
        ContactCard card = new ContactCard(contact);
        card.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showContactDetailsScreen(card);
            event.consume();
        });

        return  card;
    }
}