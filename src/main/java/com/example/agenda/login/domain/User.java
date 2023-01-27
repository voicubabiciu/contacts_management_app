package com.example.agenda.login.domain;

import com.example.agenda.viewcontacts.domain.Contact;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, columnDefinition = "text")
    private String firstName;
    @Column(nullable = false, columnDefinition = "text")
    private String lastName;
    @Column(nullable = false, columnDefinition = "varchar(128)")
    private String email;
    @Column(nullable = false, columnDefinition = "text")
    private String password;
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT NOW()")
    private Date creationDate;
    @Column(columnDefinition = "DATETIME")
    private Date confirmationDate;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    public User(String firstName, String lastName, String email, String password, Date creationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
    }

    public User() {

    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getRole() {
        return "USER";
    }
}
