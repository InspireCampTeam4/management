package minju;

import java.util.*;

public class Movie {

    private String imdb;
    private String title;
    private String director;
    private List<String> actors;
    private String genre;
    private int year;

    public Movie(String imdb, String title, String director, String genre, int year, List<String> actors) {
        this.imdb = imdb;
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.genre = genre;
        this.year = year;
    }

    // Getters
    public String getImdb() {
        return imdb;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }
    
    public List<String> getActors() {
        return actors;
    }
    
    public String getGenre() {
    	return genre;
    }

    public int getYear() {
        return year;
    }
    
    @Override
    public String toString() {
        return "Movie{" +
                "IMDB='" + imdb + '\'' +
                ", 제목='" + title + '\'' +
                ", 감독='" + director + '\'' +
                ", 장르='" + genre + '\'' +
                ", 연도=" + year +
                ", 배우=" + String.join(", ", actors) + // 배우 리스트를 쉼표로 구분된 문자열로 변환
                '}';
    }


}
