package pl.fidiym.contacts.guicomponents;

import pl.fidiym.contacts.logic.Contact;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ContactCard extends Pane {
    private Contact contact;
    private Image picture;

    public ContactCard(Contact contact) {
        this.contact = contact;

        setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        getStyleClass().add("contact-card");
        setWidth(200);
        setHeight(250);

        create_components();
    }

    private void create_components() {
        // profile picture
        this.picture = new Image(getClass().getResource("/pl/fidiym/images/contact_picture_img.png").toExternalForm());
        ImageView image = new ImageView(picture);
        image.setLayoutX(20);
        image.setLayoutY(15);

        // name text
        Label nameLabel = new Label(contact.get_name());
        nameLabel.setPrefWidth(180);
        nameLabel.setPrefHeight(25);
        nameLabel.setLayoutX(10);
        nameLabel.setLayoutY(175);
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setFont(new Font("Calibri Bold", 20));

        // number text
        Label numberLabel = new Label(contact.getNumber());
        numberLabel.setPrefWidth(180);
        numberLabel.setPrefHeight(25);
        numberLabel.setLayoutX(10);
        numberLabel.setLayoutY(200);
        numberLabel.setAlignment(Pos.CENTER);
        numberLabel.setFont(new Font("Calibri Regular", 20));

        getChildren().addAll(image, nameLabel, numberLabel);
    }

    public Contact getContact() {
        return contact;
    }

    public Image getPicture() {
        return picture;
    }
}
