package phonebook.controller;

import phonebook.service.PhoneBookService;
import phonebook.view.PhoneBookView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PhoneBookController {
    private final PhoneBookService phoneBookService;
    private final PhoneBookView phoneBookView;

    public PhoneBookController(PhoneBookService phoneBookService, PhoneBookView phoneBookView) {
        this.phoneBookService = phoneBookService;
        this.phoneBookView = phoneBookView;
    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            phoneBookView.displayPhoneBook();
            int choice = 0;
            try {
                choice = Integer.parseInt(br.readLine());
            } catch (NumberFormatException ignored) {
            }
            int index;
            switch (choice) {
            case 1:
                phoneBookService.addContact(phoneBookView.getContact());
                break;
            case 2:
                phoneBookView.displayContacts(phoneBookService.searchContactByKeyword(phoneBookView.getKeyword()));
                break;
            case 3:
                phoneBookView.displayContacts(phoneBookService.getAllContacts());
                phoneBookView.displayDetailContactNotice();
                try {
                    index = Integer.parseInt(br.readLine());
                } catch (NumberFormatException e) {
                    break;
                }
                phoneBookService.removeContact(index);
                break;
            case 4:
                phoneBookView.displayContacts(phoneBookService.getAllContacts());
                if (!phoneBookService.getAllContacts().isEmpty()) {
                    phoneBookView.displayDetailContactNotice();
                    try {
                        index = Integer.parseInt(br.readLine());
                    } catch (NumberFormatException e) {
                        break;
                    }
                    phoneBookView.displayContact(phoneBookService.getContactByIndex(index));
                }
                break;
            case 5:
                System.out.println("Thank you for using Phonebook");
                return;
            default:
                System.out.println("Invalid choice");
                break;
            }
        }
    }
}
