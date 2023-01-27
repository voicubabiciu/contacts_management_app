package com.example.agenda.viewcontacts.domain;

import com.example.agenda.login.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactPayload {

    private Integer id;

    private Integer previewContactId;
    private User user;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    private String phoneNumber;
    @Email(message = "Invalid email address", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    private String address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contact toContact() {
        final Contact contact = new Contact();
        contact.setId(id);
        contact.setUser(user);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setEmail(email);
        contact.setPhoneNumber(phoneNumber);
        contact.setAddress(address);
        return contact;
    }
    public static ContactPayload fromContact(Contact contact) {
        final ContactPayload payload = new ContactPayload();
        payload.setId(contact.getId());
        payload.setUser(contact.getUser());
        payload.setFirstName(contact.getFirstName());
        payload.setLastName(contact.getLastName());
        payload.setEmail(contact.getEmail());
        payload.setPhoneNumber(contact.getPhoneNumber());
        payload.setAddress(contact.getAddress());
        return payload;
    }

    public Integer getPreviewContactId() {
        return previewContactId;
    }

    public void setPreviewContactId(Integer previewContactId) {
        this.previewContactId = previewContactId;
    }
}
