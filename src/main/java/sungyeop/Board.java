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
            Post deleteTarget = posts.stream()
                    .filter(post -> post.getPostNumber() == postNumber)
                    .findFirst()
                    .orElse(null);

            if(deleteTarget != null) {
                posts.remove(deleteTarget);
                System.out.println("성공적으로 삭제되었습니다.");
                System.out.println("삭제 : " + deleteTarget);
                return;
            } else{
                System.out.println("삭제할 대상이 없습니다.");
                return;
            }
        }

        Post deleteTarget = posts.stream()
                .filter(post -> post.getTitle().equals(input))
                .findFirst()
                .orElse(null);

        if(deleteTarget != null) {
            posts.remove(deleteTarget);
            System.out.println("성공적으로 삭제되었습니다.");
            System.out.println("삭제 : " + deleteTarget);
            return;
        } else {
            System.out.println("삭제할 대상이 없습니다.");
        }
    }

    public void viewOnePost(Scanner scanner){
        System.out.print("확인하고 싶은 포스트명 혹은 포스트 번호를 입력해주세요 : ");
        String input = scanner.nextLine();

        if(input.matches("\\d+")) {
            int postNumber = Integer.parseInt(input);
            Post target = posts.stream()
                    .filter(post -> post.getPostNumber() == postNumber)
                    .findFirst()
                    .orElse(null);
            if(target != null){
                printPost(target);
                postStatistics.put(target, postStatistics.get(target) + 1);
                return;
            }
            System.out.println("해당 포스트가 없습니다.");
            return;
        }

        Post target = posts.stream()
                .filter(post -> post.getTitle().equals(input))
                .findFirst()
                .orElse(null);

        if(target != null){
            printPost(target);
            postStatistics.put(target, postStatistics.get(target) + 1);
            return;
        }
        System.out.println("해당 포스트가 없습니다.");
    }

    public void viewAllStatistics() {
        postStatistics.forEach((key, value) -> printStatistics(key, value));
    }

    public void printStatistics(Post post, int view) {
        System.out.println("#########");
        System.out.println("Post # " + post.getPostNumber());
        System.out.println("포스트 제목 : " + post.getTitle());
        System.out.println("조회수 : " + view);
        System.out.println("#########\n");
    }

    public void viewOneStatistics(Scanner scanner){
        System.out.print("검색하고 싶은 포스트의 제목 혹은 번호를 입력해주세요 : ");
        String input = scanner.nextLine();

        if(input.matches("\\d+")) {
            int postNumber = Integer.parseInt(input);
            Map.Entry<Post, Integer> find = postStatistics.entrySet().stream()
                    .filter(post -> post.getKey().getPostNumber() == postNumber)
                    .findFirst()
                    .orElse(null);

            if(find != null){
                printStatistics(find.getKey(), find.getValue());
                return;
            }
            System.out.println("대상이 없습니다.");
            return;
        }

        Map.Entry<Post, Integer> find = postStatistics.entrySet().stream()
                .filter(post -> post.getKey().getTitle().equals(input))
                .findFirst()
                .orElse(null);
        if(find != null){
            printStatistics(find.getKey(), find.getValue());
            return;
        }
        System.out.println("대상이 없습니다.");
    }
}
