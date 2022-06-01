package pl.fidiym.contacts.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void is_right_number_format() {
        String[] numbersCorrect = new String[]{"123 123", "5348 2392 23949294239432", "(12) 12", "1", "98942 (123) 123", "123-123"};
        String[] numbersIncorrect = new String[]{"asd", "123 24d", "123 24 (2)", "79 oo 123", "8071823 123123.", "123x123x123"};

        for (String number : numbersCorrect) {
            assertTrue(Contact.is_right_number_format(number));
        }

        for (String number : numbersIncorrect) {
            System.out.println(number);
            assertFalse(Contact.is_right_number_format(number));
        }
    }
}