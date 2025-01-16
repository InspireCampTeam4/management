package sungyeop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Scanner;

@AllArgsConstructor
@Getter
@ToString
public class Post {
    private String title;
    private String content;
    private String author;
    private String date;

    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        board.addPost(scanner);

    }
}
