package sungyeop;

import java.util.*;
import java.util.function.Consumer;

public class Board {
    private final List<Post> posts = new ArrayList<>();
    private final Map<Post, Integer> postStatistics = new HashMap<>();
    private static int postNumber = 1;

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
        posts.forEach(System.out::println);
    }

    private boolean isIntInput(String input){
        return input.matches("\\d+");
    }

    private Post findPostUsingPostNumber(int postNumber) {
        return posts.stream()
                .filter(post -> post.getPostNumber() == postNumber)
                .findFirst()
                .orElse(null);
    }

    private Post findPostUsingTitle(String title) {
        return posts.stream()
                .filter(post -> post.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    private Post findPostUsingByInput(String input){
        if(isIntInput(input)){
            return findPostUsingPostNumber(Integer.parseInt(input));
        }
        return findPostUsingTitle(input);
    }

    private void processDelete(Post target){
        if(target == null) {
            System.out.println("삭제할 대상이 없습니다.");
            return;
        }
        posts.remove(target);
        System.out.println("성공적으로 삭제되었습니다.");
        System.out.println("삭제된 포스트 번호 : " + target.getPostNumber());
    }

    public void deletePost(Scanner scanner) {
        System.out.print("삭제하고 싶은 포스트명 혹은 포스트 번호를 말씀해주세요 : ");
        String input = scanner.nextLine();

        Post target = findPostUsingByInput(input);
        processDelete(target);
    }

    private void processView(Post target) {
        if(target == null){
            System.out.println("대상 포스팅이 없습니다.");
            return;
        }
        System.out.println(target);
        postStatistics.put(target, postStatistics.get(target) + 1);
    }

    public void viewOnePost(Scanner scanner){
        System.out.print("확인하고 싶은 포스트명 혹은 포스트 번호를 입력해주세요 : ");
        String input = scanner.nextLine();

        Post target = findPostUsingByInput(input);
        processView(target);

    }

    public void viewAllStatistics() {
        postStatistics.forEach(this::printStatistics);
    }

    public void printStatistics(Post post, int view) {
        System.out.println("#########");
        System.out.println("Post # " + post.getPostNumber());
        System.out.println("포스트 제목 : " + post.getTitle());
        System.out.println("조회수 : " + view);
        System.out.println("#########\n");
    }

    private Map.Entry<Post, Integer> findPostStatistics(Post post) {
        return postStatistics.entrySet().stream()
                .filter(entry -> entry.getKey().equals(post))
                .findFirst()
                .orElse(null);
    }

    private void processViewOneStatistics(Post post) {
        if(post == null){
            System.out.println("대상 포스팅이 존재하지 않습니다.");
            return;
        }
        Map.Entry<Post, Integer> target = findPostStatistics(post);

        System.out.println("Post # : " + target.getKey().getPostNumber());
        System.out.println("제목 : " + target.getKey().getTitle());
        System.out.println("View : " + target.getValue());
    }


    public void viewOneStatistics(Scanner scanner){
        System.out.print("검색하고 싶은 포스트의 제목 혹은 번호를 입력해주세요 : ");
        String input = scanner.nextLine();

        Post target = findPostUsingByInput(input);
        processViewOneStatistics(target);
    }
}
