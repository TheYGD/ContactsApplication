package pl.fidiym.contacts.controllers;

import pl.fidiym.contacts.logic.Contact;

public interface ContactInfoController extends SceneController {
    void init();
    Contact add_contact();
    void fill_fields(Contact contact);
}
