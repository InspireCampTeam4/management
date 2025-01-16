package sungyeop;

import java.util.*;

public class Board {
    private List<Post> posts = new ArrayList<>();
    private Map<Post, Integer> postStatistics = new HashMap<>();
    private static int postNumber = 1;

    private static void printPost(Post post) {
        System.out.print("\n##########################################\n");
        System.out.println("Post # " + post.getPostNumber());
        System.out.println("포스트 제목 : " + post.getTitle());
        System.out.println("포스트 작성 날짜 : " + post.getDate());
        System.out.println("포스트 작성자 : " + post.getAuthor());
        System.out.println("포스트 내용 : " + post.getContent());
        System.out.println("##########################################");
    }

    public void addPost(Scanner scanner) {
        System.out.print("포스트 제목을 입력해주세요 : ");
        String title = scanner.nextLine();

        System.out.print("포스트 작성 날짜를 입력해주세요 (YYYY-MM-DD) : ");
        String date;
        while(true) {
            date = scanner.nextLine();
            if(date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                break;
            }
            System.out.println("날짜 형식을 맞춰서 다시 입력해주세요");
        }

        System.out.print("포스트 작성자를 입력해주세요 : ");
        String author = scanner.nextLine();

        System.out.println("포스트 내용을 입력해주세요");
        String content = scanner.nextLine();

        Post post = new Post(title, content, author, date, postNumber++);
        posts.add(post);
        postStatistics.put(post, 0);
    }

    public void printAllPosts() {
        if(posts.isEmpty()){
            System.out.println("등록된 포스트가 없습니다.");
            return;
        }
        posts.forEach(Board::printPost);
    }

    public void deletePost(Scanner scanner) {
        System.out.print("삭제하고 싶은 포스트명 혹은 포스트 번호를 말씀해주세요 : ");
        String input = scanner.nextLine();

        if(input.matches("\\d+")) {
            int postNumber = Integer.parseInt(input);
            posts.removeIf(post -> post.getPostNumber() == postNumber);
            return;
        }
        posts.removeIf(post -> post.getTitle().equals(input));
    }
}
