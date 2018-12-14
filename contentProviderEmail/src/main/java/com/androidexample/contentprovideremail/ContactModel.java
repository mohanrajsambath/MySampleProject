package com.androidexample.contentprovideremail;

/**
 * Created by innobot-linux-7 on 1/10/18,01,MyApplication.
 */
public class ContactModel {


    String name;
    String firstname;
    String lastname;
    String companyname;

    public ContactModel(String displayName, String companyName, String emailAddress) {

        this.name = displayName;
        this.companyname = companyName;
        this.emailaddress = emailAddress;
    }


    public ContactModel( String emailAddress) {

        this.emailaddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname=firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname=lastname;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname=companyname;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress=emailaddress;
    }

    String emailaddress;
}
