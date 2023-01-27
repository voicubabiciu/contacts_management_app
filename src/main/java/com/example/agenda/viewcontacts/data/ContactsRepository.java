package com.example.agenda.viewcontacts.data;

import com.example.agenda.viewcontacts.domain.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByUserIdOrderByFirstName(Integer userId);
    @Transactional
    void removeContactsById(Integer id);
    Contact findContactById(Integer id);
}
