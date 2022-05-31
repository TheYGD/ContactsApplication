package pl.fidiym.contacts.logic;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactBook {
    private ArrayList<Contact> book;
    private final String fileName = "contacts.bin";

    public ContactBook() {
        book = new ArrayList<>();
        deserialize();
    }

    public void add_contact(Contact contact) {
        book.add(contact);
        serialize();
    }

    public void remove(Contact contact) {
        book.remove(contact);
        serialize();
    }

    private void serialize() {
        try (ObjectOutputStream ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            ous.writeObject(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deserialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            ArrayList<Contact> fromFile = (ArrayList<Contact>) ois.readObject();
            if (!fromFile.isEmpty()) {
                System.out.println("open phonebook.db\n");
                book = fromFile;
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    public ArrayList<Contact> get_contacts() {
        return book;
    }

    public ArrayList<Contact> matching_contacts(String regex) {
        ArrayList<Contact> results = new ArrayList<>();

        for (Contact contact : book) {
            Matcher matcher = Pattern.compile("(?i)" + regex).matcher(contact.get_name() + " " + contact.getNumber());
            if (matcher.find()) {
                results.add(contact);
            }
        }

        return results;
    }
}
