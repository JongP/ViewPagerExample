package com.example.viewpagerexample;

public class ContactItem {

    private String contactName, contactNum, contactThumbnail;

    public ContactItem(String contactName, String contactNum, String contactThumbnail) {
        this.contactName = contactName;
        this.contactNum = contactNum;
        this.contactThumbnail = contactThumbnail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getContactThumbnail() {
        return contactThumbnail;
    }

    public void setContactThumbnail(String contactThumbnail) {
        this.contactThumbnail = contactThumbnail;
    }
}
