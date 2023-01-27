package com.example.agenda.viewcontacts.presentation;

import com.example.agenda.login.data.UserService;
import com.example.agenda.viewcontacts.data.ContactsService;

import com.example.agenda.viewcontacts.domain.Contact;
import com.example.agenda.viewcontacts.domain.ContactPayload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String getAllContacts(@RequestParam(required = false) Integer selectedContactId, @RequestParam(required = false) Integer editId, Model model) {
        model.addAttribute("contacts", contactsService.getContacts(userService.getCurrentUser().getId()));
        if (editId != null && editId != -1) {
            final Contact contact = contactsService.getContact(editId);
            final ContactPayload payload = ContactPayload.fromContact(contact);
            if (selectedContactId != null && selectedContactId != -1) {
                payload.setPreviewContactId(selectedContactId);
            }
            model.addAttribute("contactPayload", payload);
            model.addAttribute("editId", editId);
        } else {
            final ContactPayload payload = new ContactPayload();
            if (selectedContactId != null && selectedContactId != -1) {
                payload.setPreviewContactId(selectedContactId);
            }
            model.addAttribute("contactPayload", payload);
        }
        if (selectedContactId != null && selectedContactId != -1) {
            model.addAttribute("selectedContact", contactsService.getContact(selectedContactId));
        }
        return "contacts";
    }

    @PostMapping(path = "/addOrUpdate")
    public String addOrUpdate(@Valid ContactPayload payload, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("contacts", contactsService.getContacts(userService.getCurrentUser().getId()));
            if (payload.getPreviewContactId() != null) {
                model.addAttribute("selectedContact", contactsService.getContact(payload.getPreviewContactId()));
            }
            return "contacts";
        }
        payload.setUser(userService.getCurrentUser());
        if (payload.getId() != null) {
            contactsService.updateContact(payload.toContact());
        } else {
            contactsService.addNewContact(payload);
        }

        if (payload.getPreviewContactId() != null) {
            return "redirect:all?selectedContactId=" + payload.getPreviewContactId();

        }
        return "redirect:all";
    }


    @GetMapping(path = "/delete")
    public String delete(@RequestParam Integer id, @RequestParam(required = false) Integer selectedContactId, @RequestParam(required = false) Integer editId, Model model) {
        contactsService.deleteContact(id);

        if (selectedContactId != null && selectedContactId != -1) {
            if (editId != null && editId != -1 && editId != id) {
                return "redirect:all?selectedContactId=" + selectedContactId + "&editId=" + editId;
            }
            return "redirect:all?selectedContactId=" + selectedContactId;
        }
        if (editId != null && editId != -1 && editId != id) {
            return "redirect:all?editId=" + editId;
        }
        return "redirect:all";
    }

    @GetMapping(path = "/edit")
    public String edit(@RequestParam() Integer id, @RequestParam(required = false) Integer selectedContactId, Model model) {
        if (selectedContactId != null && selectedContactId != -1) {
            return "redirect:all?editId=" + id + "&selectedContactId=" + selectedContactId;
        }
        return "redirect:all?editId=" + id;
    }


}