package pl.fidiym.contacts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.fidiym.contacts.controllers.ContactsController;

import java.io.IOException;

public class ContactsApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ContactsApplication.class.getResource("contactsapplication/contacts-view.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(ContactsApplication.class.getResource("hello-view.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(ContactsApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Contacts");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/pl/fidiym/styles/contacts-style.css").toExternalForm());

        ContactsController controller = fxmlLoader.getController();
        controller.init();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}