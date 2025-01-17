package sunwoo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sunwoo.service.IdGenerator;

@Getter
public class User extends Item{
    private String username;
    private String password;
    private Band band;

    public User(String username, String password, Band band) {
        super(IdGenerator.generateUserId(username));
        this.username = username;
        this.password = password;
        this.band = band;
    }
}
