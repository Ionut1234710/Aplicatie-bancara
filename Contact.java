package Etapa1;

public class Contact {

    String phone_number;
    String mail_address;
    String facebook_address;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getMail_address() {
        return mail_address;
    }

    public void setMail_address(String mail_address) {
        this.mail_address = mail_address;
    }

    public String getFacebook_address() {
        return facebook_address;
    }

    public void setFacebook_address(String facebook_address) {
        this.facebook_address = facebook_address;
    }

    public Contact(String phone_number, String mail_address, String facebook_address) {
        this.phone_number = phone_number;
        this.mail_address = mail_address;
        this.facebook_address = facebook_address;
    }
}
