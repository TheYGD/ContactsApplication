# ContactsApplication
By Jakub Szmidla

Desktop Application made with JavaFX.
It is a contact book. You can add contacts using a "+" button in the left bottom of the screen.
You can choose whether the contact is a person or an organization. After the data validation, the contact
is added and from now you can search for it by typing a pattern into the search field.

Number must must fit the following pattern:
- it has to contatain only digits, which can be seperated with space or dash,
- one of the 2 first number sections (digits seperated with space/dash) can be placed within parentheses (),
- there is no specified number's length

It is possible to edit and delete contacts.
The contact list is being serialized and saved to the "contacts.bin" file.

The main class is pl\fidiym\contacts\ContactsApplication.java
