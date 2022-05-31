module pl.fidiym.contacts {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.fidiym.contacts to javafx.fxml;
    exports pl.fidiym.contacts;
    opens pl.fidiym.contacts.controllers to javafx.fxml;
    exports pl.fidiym.contacts.controllers;
}