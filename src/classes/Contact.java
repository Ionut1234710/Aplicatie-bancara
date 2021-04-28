package classes;

public class Contact {

    private String phone_number;
    private String mail_address;
    private String facebook_address;

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

    public Contact() {
        this.phone_number="";
        this.mail_address="";
        this.facebook_address="";
    }

    public Contact(String phone_number, String mail_address, String facebook_address) {
        this.phone_number = phone_number;
        this.mail_address = mail_address;
        this.facebook_address = facebook_address;
    }

    @Override
    public String toString(){
        return "Contact:  " + "Phone_Number = " + phone_number + ",  Mail_Address = " + mail_address + ",  Facebook_Address = " + facebook_address;
    }

}
