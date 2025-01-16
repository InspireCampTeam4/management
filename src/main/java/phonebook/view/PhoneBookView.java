package phonebook.view;

import phonebook.model.Contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class PhoneBookView {
    public void displayPhoneBook() {
        System.out.println();
        System.out.println("Jeongbeen's Phone Book");
        System.out.println("1. 연락처 추가");
        System.out.println("2. 연락처 검색");
        System.out.println("3. 연락처 삭제");
        System.out.println("4. 연락처 목록");
        System.out.println("5. 종료");
    }

    public void displayContacts(List<Contact> contactList) {
        System.out.println();
        if (contactList.isEmpty()) {
            System.out.println("No contact found");
            return;
        }
        System.out.printf("%-5s %-20s %-15s%n", "Index", "Name", "Phone Number");
        int index = 0;
        for (Contact contact : contactList) {
            System.out.printf("%-5d %-20s %-15s%n", index, contact.getName(), contact.getPhone());
            index++;
        }
        System.out.println();
    }

    public void displayContact(Contact contact) {
        System.out.println();
        System.out.println("Name: " + contact.getName());
        System.out.println("NickName: " + contact.getNickName());
        System.out.println("Age: " + contact.getAge());
        System.out.println("Phone Number: " + contact.getPhone());
    }

    public Contact getContact() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name;
        String nickName;
        int age;
        String phone;

        System.out.println("Enter name: ");
        name = reader.readLine();
        System.out.println("Enter nickname: ");
        nickName = reader.readLine();
        System.out.println("Enter age: ");
        age = Integer.parseInt(reader.readLine());
        System.out.println("Enter phone number: ");
        phone = reader.readLine();

        return new Contact(name, nickName, age, phone);
    }

    public String getKeyword() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter name or nickname: ");
        return reader.readLine();
    }

    public void displayDetailContactNotice() {
        System.out.println("Enter index: ");
    }
}
