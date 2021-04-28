package classes;

import operations.Service;

public class Customer implements Service {

    private String id;
    private String last_name;
    private String first_name;
    private String cnp;
    private String address;
    private String email;

    public Customer(){
        this.id="";
        this.last_name="";
        this.first_name="";
        this.cnp="";
        this.address="";
        this.email="";
    }

    public Customer(String last_name, String first_name, String cnp, String address, String email) {
        this.id = genID();
        this.last_name = last_name;
        this.first_name = first_name;
        this.cnp = cnp;
        this.address = address;
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "Customer:  " + "ID = " + id + ",  Last_Name = " + last_name + ",  First_Name = " + first_name +
                ",  CNP = " + cnp + ",  Address = " + address + ",  Email = " + email;
    }

    @Override
    public String getId(){
        return id;
    }
}
