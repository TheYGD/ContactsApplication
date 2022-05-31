package pl.fidiym.contacts.logic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private String number;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdit;

    protected Contact(String number) {
        this.number = number;
        timeCreated = LocalDateTime.now();
        timeLastEdit = LocalDateTime.now();
    }

    public static boolean is_right_number_format(String number) {
        if (number == null) {
            return false;
        }

        String noParentheses = "\\+?\\d+([ -]\\d{2,})*";
        String firstParentheses = "\\+?\\(\\d+\\)([ -]\\d{2,})*";
        String secondParentheses = "\\+?\\d+[ -]\\(\\d{2,}\\)([ -]\\d{2,})*";

        String regex = "(" + noParentheses + "|" + firstParentheses + "|" + secondParentheses + ")";

        Matcher matcher = Pattern.compile(regex).matcher(number);
        return matcher.matches();
    }

    //
    // GETTERS & SETTERS
    //------------------------------------------------------------------------
    public String getNumber() {
        return number;
    }

    protected String getTimeCreated() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return timeCreated.format(formatter);
    }

    protected String getTimeLastEdit() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return timeLastEdit.format(formatter);
    }

    abstract public String get_name();
    //------------------------------------------------------------------------

    public abstract String getInfoFXML();
}
