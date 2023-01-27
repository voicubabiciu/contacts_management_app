package com.example.agenda.viewcontacts.presentation;

import com.example.agenda.login.data.UserService;
import com.example.agenda.register.domain.RegisterPayload;
import com.example.agenda.viewcontacts.data.ContactsService;

import com.example.agenda.viewcontacts.domain.Contact;
import com.example.agenda.viewcontacts.domain.ContactPayload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping(path = "/contacts")
public class ViewContactsController {
    private final ContactsService contactsService;
    private final UserService userService;

    @Autowired
    public ViewContactsController(ContactsService bookRepository, UserService userService) {
        this.contactsService = bookRepository;
        this.userService = userService;
    }

    @GetMapping(path = "/all")
    public String getAllContacts(@RequestParam(required = false) Integer selectedContactId,  Model model) {
        model.addAttribute("contacts", contactsService.getContacts(userService.getCurrentUser().getId()));
        model.addAttribute("contactPayload", new ContactPayload());
        model.addAttribute("selectedContact", contactsService.getContact(selectedContactId));
        return "contacts";
    }

    @PostMapping(path = "/add")
    public String add(@Valid ContactPayload payload, @RequestParam(required = false) Integer selectedContactId, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("contacts", contactsService.getContacts(userService.getCurrentUser().getId()));
            model.addAttribute("selectedContact", contactsService.getContact(selectedContactId));
            return "contacts";
        }
        contactsService.addNewContact(payload, userService.getCurrentUser());

        model.addAttribute("contacts", contactsService.getContacts(userService.getCurrentUser().getId()));
        model.addAttribute("contactPayload", new ContactPayload());
        model.addAttribute("contactId", null);
        model.addAttribute("selectedContact", contactsService.getContact(selectedContactId));
        return "contacts";
    }

    @PostMapping(path = "/update")
    public String update(@Valid ContactPayload payload, @RequestParam Integer contactId, @RequestParam(required = false) Integer selectedContactId, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("contacts", contactsService.getContacts(userService.getCurrentUser().getId()));
            model.addAttribute("contactId", contactId);
            model.addAttribute("selectedContact", contactsService.getContact(selectedContactId));
            return "contacts";
        }
        payload.setUser(userService.getCurrentUser());
        payload.setId(contactId);
        contactsService.updateContact(payload.toContact());
        model.addAttribute("contacts", contactsService.getContacts(userService.getCurrentUser().getId()));
        model.addAttribute("contactPayload", new ContactPayload());
        model.addAttribute("contactId", null);
        model.addAttribute("selectedContact", contactsService.getContact(selectedContactId));
        return "contacts";
    }

    @GetMapping(path = "/delete")
    public String delete(@RequestParam Integer id, @RequestParam(required = false) Integer selectedContactId, Model model) {
        contactsService.deleteContact(id);
        model.addAttribute("contacts", contactsService.getContacts(userService.getCurrentUser().getId()));
        model.addAttribute("contactPayload", new ContactPayload());
        model.addAttribute("contactId", null);
        model.addAttribute("selectedContact", contactsService.getContact(selectedContactId));

        return "contacts";
    }

    @GetMapping(path = "/edit")
    public String edit(@RequestParam Integer id, @RequestParam(required = false) Integer selectedContactId, Model model) {
        final Contact contact = contactsService.getContact(id);
        final ContactPayload payload = ContactPayload.fromContact(contact);
        model.addAttribute("contactPayload", payload);
        model.addAttribute("contacts", contactsService.getContacts(userService.getCurrentUser().getId()));
        model.addAttribute("contactId", id);
        model.addAttribute("selectedContact", contactsService.getContact(selectedContactId));

        return "contacts";
    }


}