package pl.fidiym.contacts.logic;

public class OrganizationContact extends Contact {
    private String organizationName;
    private String address;

    public OrganizationContact(String number, String organizationName, String address) {
        super(number);
        this.organizationName = organizationName;
        this.address = address;
    }

    //
    // GETTERS & SETTERS
    //--------------------------------------------------------------------------------
    public String getOrganizationName() {
        return organizationName;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String get_name() {
        return organizationName;
    }
    //--------------------------------------------------------------------------------

    @Override
    public String getInfoFXML() {
        return "/pl/fidiym/contacts/otherscreens/organization-addcontact-view.fxml";
    }

    @Override
    public String toString() {
        return "Organization name: " + getOrganizationName() +
                "\nNumber: " + getNumber() +
                "\nAddress: " + getAddress() +
                "\n" + super.toString();
    }
}