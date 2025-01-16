package minju;

import java.util.*;

public class MovieManagement {
    // 영화 정보
    List<Movie> movies = new ArrayList<>();

    // IMDB => 영화 등록 시 동일 영화가 존재하는지 확인
    private Set<String> imdbSet = new HashSet<>();

    // 조회수 => IMDB, 조회수 정보를 관리
    private Map<String, Integer> movieViews = new HashMap<>();

    // 1. 영화 등록
    public void addMovie(Scanner scanner) {
        System.out.print("IMDB: ");
        String imdb = scanner.nextLine();

        if (imdbSet.contains(imdb)) {
            System.out.printf("IMDB %s과 동일한 영화가 이미 등록되어 있습니다.\n", imdb);
            return;
        }

        System.out.print("제목: ");
        String title = scanner.nextLine();

        System.out.print("감독: ");
        String director = scanner.nextLine();
        
        // 출연 배우
        List<String> actors = new ArrayList<>();
        System.out.println("출연 배우를 입력하세요. 'exit'을 입력하면 종료됩니다.");
        int actorCount = 1; // 배우 카운트 

        while (true) {
            System.out.print("배우" + actorCount + ": "); 
            String actor = scanner.nextLine();
            if ("exit".equalsIgnoreCase(actor)) {
                break;
            }
            actors.add(actor);
            actorCount++; 
        }
        
        System.out.print("장르: ");
        String genre = scanner.nextLine();
        

        System.out.print("연도: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        

        Movie movie = new Movie(imdb, title, director, genre, year, actors);

        movies.add(movie);
        imdbSet.add(movie.getImdb());
        movieViews.put(movie.getImdb(), 0);
        System.out.println("정상적으로 등록되었습니다." + movie);
    }

    // 2. 전체 영화 목록 조회
    public void listMovie() {
        if (movies.isEmpty()) {
            System.out.println("등록된 영화가 없습니다.");
            return;
        }

        movies.forEach(System.out::println);
    }

    // 3. 영화 검색 (IMDB)
    public void searchByImdb(Scanner scanner) {
        System.out.print("IMDB ID를 입력하세요: ");
        String imdb = scanner.nextLine().trim();

        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getImdb().equalsIgnoreCase(imdb)) {
                System.out.println(movie);
                movieViews.put(movie.getImdb(), movieViews.get(movie.getImdb()) + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.printf("IMDB ID '%s'와 일치하는 영화를 찾을 수 없습니다.\n", imdb);
        }
    }

    // 4. 영화 검색 (감독)
    public void searchByDirector(Scanner scanner) {
        System.out.print("감독 이름을 입력하세요: ");
        String director = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getDirector().toLowerCase().contains(director)) {
                System.out.println(movie);
                movieViews.put(movie.getImdb(), movieViews.get(movie.getImdb()) + 1);
                found = true;
            }
        }

        if (!found) {
            System.out.printf("'%s'를 감독으로 하는 영화를 찾을 수 없습니다.\n", director);
        }
    }

    // 5. 영화 검색 (배우)
    public void searchByActor(Scanner scanner) {
        System.out.print("배우 이름을 입력하세요: ");
        String actor = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getActors().stream().anyMatch(a -> a.toLowerCase().contains(actor))) {
                System.out.println(movie);
                movieViews.put(movie.getImdb(), movieViews.get(movie.getImdb()) + 1);
                found = true;
            }
        }

        if (!found) {
            System.out.printf("'%s'가 출연한 영화를 찾을 수 없습니다.\n", actor);
        }
    }

    // 6. 영화 검색 (장르)
    public void searchByGenre(Scanner scanner) {
        System.out.print("장르를 입력하세요: ");
        String genre = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getGenre().toLowerCase().contains(genre)) {
                System.out.println(movie);
                movieViews.put(movie.getImdb(), movieViews.get(movie.getImdb()) + 1);
                found = true;
            }
        }

        if (!found) {
            System.out.printf("장르 '%s'의 영화를 찾을 수 없습니다.\n", genre);
        }
    }

    // 7. 영화 삭제
    public void deleteMovie(Scanner scanner) {
        System.out.print("삭제할 영화의 IMDB을 입력하세요: ");
        String imdb = scanner.nextLine();

        Iterator<Movie> iterator = movies.iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            if (movie.getImdb().equals(imdb)) {
                iterator.remove();
                imdbSet.remove(imdb);
                movieViews.remove(imdb);
                System.out.printf("IMDB %s 영화를 삭제했습니다.\n", imdb);
                return;
            }
        }

        System.out.printf("IMDB %s와 일치하는 영화를 찾을 수 없습니다.\n", imdb);
    }

    // 8. 인기 영화 조회
    public void mostViewedMovies() {
        if (movieViews.isEmpty()) {
            System.out.println("등록된 영화가 없습니다.");
            return;
        }

        int maxViews = Collections.max(movieViews.values());
        if (maxViews == 0) {
            System.out.println("조회된 영화가 없습니다.");
            return;
        }

        for (Map.Entry<String, Integer> entry : movieViews.entrySet()) {
            if (entry.getValue() == maxViews) {
                movies.stream()
                        .filter(movie -> movie.getImdb().equals(entry.getKey()))
                        .forEach(System.out::println);
            }
        }
    }
    
    public void runMenu(Scanner scanner) {
        boolean running = true;

        while (running) {
        	System.out.println("");
            System.out.println("-------------------");
            System.out.println("채민주 | 영화관리시스템 ");
            System.out.println("-------------------");
            System.out.println("1. 영화 등록");
            System.out.println("2. 전체 영화 출력");
            System.out.println("3. 영화 검색(IMDB)");
            System.out.println("4. 영화 검색(감독)");
            System.out.println("5. 영화 검색(배우)");
            System.out.println("6. 영화 검색(장르)");
            System.out.println("7. 영화 삭제");
            System.out.println("8. 인기 영화 조회");
            System.out.println("9. 종료");
            System.out.print("메뉴를 선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addMovie(scanner);
                    break;
                case 2:
                    listMovie();
                    break;
                case 3:
                	searchByImdb(scanner);
                    break;
                case 4:
                	searchByDirector(scanner);
                	break;
                case 5:
                	searchByActor(scanner);
                	break;
                case 6:
                	searchByGenre(scanner);
                	break;
                case 7:
                    deleteMovie(scanner);
                    break;
                case 8:
                    mostViewedMovies();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 입력하세요.");
            }
        }
        
    }
}
