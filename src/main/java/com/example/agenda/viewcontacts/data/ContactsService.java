package com.example.agenda.viewcontacts.data;

import com.example.agenda.login.domain.User;
import com.example.agenda.viewcontacts.domain.Contact;
import com.example.agenda.viewcontacts.domain.ContactPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactsService {
    private final ContactsRepository bookRepository;

    @Autowired
    public ContactsService(ContactsRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public List<Contact> getContacts(final Integer userId){
        return bookRepository.findByUserIdOrderByFirstName(userId);
    }
    public void deleteContact(final Integer contactId){
         bookRepository.removeContactsById(contactId);
    }
    public Contact getContact(final Integer contactId){
        return bookRepository.findContactById(contactId);
    }
    public void addNewContact(final ContactPayload payload, final User user){
        final Contact contact = payload.toContact();
        contact.setUser(user);
        bookRepository.saveAndFlush(contact);
    }

    public void updateContact(final Contact contact){
        bookRepository.save(contact);
    }
}
