package classes;

import operations.Service;

import java.util.ArrayList;
import java.util.List;

public class Bank implements Service {

    private String id;
    private String bankName;
    private Contact contact;
    List<Account> accountList;

    public Bank(){
        this.id = "";
        this.bankName = "";
        this.contact = new Contact();
        this.accountList = new ArrayList<>();
    }

    public Bank(String bankName, Contact contact, List<Account> accountList) {
        this.id = genID();
        this.bankName = bankName;
        this.contact = contact;
        this.accountList = accountList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public boolean checkAccounts(List<Account> accountList){
        return accountList.isEmpty(); //returneaza true daca lista este goala
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Bank:  " + "ID = " + id + ",  Bank name is: " + bankName + "\nThis bank has the following contact -> " + contact.toString());
        if(!checkAccounts(accountList)) {
            s.append("\nBank has " + accountList.size() + " accounts:\n");
            for(int i=0; i<accountList.size(); i++)
                s.append(accountList.get(i).toString());
        }
        else s.append("\nBank has no accounts\n");
        return s.toString();
    }

    @Override
    public String getId() { return id; }

}
