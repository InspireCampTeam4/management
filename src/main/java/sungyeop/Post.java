package sungyeop;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Post {
    private String title;
    private String content;
    private String author;
    private String date;
    private int postNumber;

    @Override
    public String toString() {
        return "\n##########################################\n" +
                "Post # " + this.getPostNumber() + "\n" +
                "포스트 제목 : " + this.getTitle() + "\n" +
                "포스트 작성 날짜 : " + this.getDate() + "\n" +
                "포스트 작성자 : " + this.getAuthor() + "\n" +
                "포스트 내용 : " + this.getContent() + "\n" +
                "##########################################";
    }
}