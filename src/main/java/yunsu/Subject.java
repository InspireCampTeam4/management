package yunsu;

public class Subject {
    private String name;
    private int credit;
    private double grade;

    public Subject(String name, int credit, double grade) {
        this.name = name;
        this.credit = credit;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "과목: " + name + ", 이수 학점: " + credit + ", 성적: " + grade;
    }
}