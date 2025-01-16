package phonebook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Contact {
    private String name;
    private String nickName;
    private int age;
    private String phone;
}
