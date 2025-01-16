package phonebook.service;

import phonebook.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneBookService {
    private final List<Contact> contactList = new ArrayList<>();

    public Contact getContactByIndex(int index) {
        if (checkIndex(index)) {
            return contactList.get(index);
        }
        return null;
    }

    public void addContact(Contact contact) {
        validateContact(contact);
        contactList.add(contact);
    }

    public void removeContact(int index) {
        if (checkIndex(index)) {
            contactList.remove(index);
        }
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contactList);
    }

    public List<Contact> searchContactByKeyword(String keyword) {
        return contactList.stream().filter(contact -> {
            String name = contact.getName();
            String nickname = contact.getNickName();
            return name.toLowerCase().contains(keyword.toLowerCase()) || nickname.toLowerCase().contains(keyword.toLowerCase());
        }).collect(Collectors.toList());
    }

    private void validateContact(Contact contact) {
        if (contact.getName() == null || contact.getNickName() == null || contact.getName().isEmpty() || contact.getNickName().isEmpty()) {
            System.out.println("Contact name or nickname is empty");
        }
        if (contact.getPhone() == null || contact.getPhone().isEmpty()) {
            System.out.println("Contact phone is empty");
        }
    }

    private boolean checkIndex(int index) {
        if (index >= 0 && index < contactList.size()) {
            return true;
        }
        System.out.println("Index out of bounds");
        return false;
    }
}
