package yunsu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubjectManager {
    private List<Subject> subjects;

    public SubjectManager() {
        this.subjects = new ArrayList<>();
    }

    // 새 과목을 추가
    public void addSubject(String name, int credit, double grade) {
        subjects.add(new Subject(name.trim(), credit, grade));
    }

    // 주어진 이름을 가진 과목을 검색하는 메서드
    public void searchSubject(String name) {
        boolean found = false;
        for (Subject subject : subjects) {
            if (subject.getName().equalsIgnoreCase(name.trim())) {
                System.out.println(subject);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("해당 과목을 찾을 수 없습니다.");
        }
    }

    // 주어진 이름을 가진 과목을 삭제하는 메서드
    public void deleteSubject(String name) {
        boolean removed = subjects.removeIf(subject -> subject.getName().trim().equalsIgnoreCase(name.trim()));
        if (removed) {
            System.out.println("과목이 삭제되었습니다.");
        } else {
            System.out.println("해당 과목을 찾을 수 없습니다.");
        }
    }

    // 저장된 모든 과목을 출력하는 메서드
    public void displaySubjects() {
        if (subjects.isEmpty()) {
            System.out.println("저장된 과목이 없습니다.");
        } else {
            // 성적에 해당 과목의 학점을 곱한 값을 합산하고 이를 총 학점으로 나누어 평균 학점을 계산
            double totalCredits = 0;
            double totalGrades = 0;
            for (Subject subject : subjects) {
                System.out.println(subject);
                totalCredits += subject.getCredit();
                totalGrades += subject.getGrade() * subject.getCredit();
            }
            double averageGrade = totalGrades / totalCredits;
            averageGrade = Math.round(averageGrade * 100.0) / 100.0; // 소수점 둘째자리

            System.out.println("총 학점: " + totalCredits);
            System.out.println("평균 학점: " + averageGrade);
        }
    }

    // 성적 관리 시스템의 메뉴를 실행하는 메서드
    public void runMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n===== 성적 관리 시스템 =====");
            System.out.println("1. 과목 추가");
            System.out.println("2. 과목 검색");
            System.out.println("3. 과목 삭제");
            System.out.println("4. 전체 과목 보기");
            System.out.println("5. 종료");
            System.out.print("메뉴를 선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("과목 이름을 입력하세요: ");
                    String name = scanner.nextLine();
                    System.out.print("이수 학점을 입력하세요: ");
                    int credit = scanner.nextInt();
                    System.out.print("성적을 입력하세요: ");
                    double grade = scanner.nextDouble();
                    addSubject(name, credit, grade);
                    System.out.println("과목이 추가되었습니다.");
                    break;
                case 2:
                    System.out.print("검색할 과목 이름: ");
                    String searchName = scanner.nextLine();
                    searchSubject(searchName);
                    break;
                case 3:
                    System.out.print("삭제할 과목 이름: ");
                    String deleteName = scanner.nextLine();
                    deleteSubject(deleteName);
                    break;
                case 4:
                    displaySubjects();
                    break;
                case 5:
                    System.out.println("성적 관리 시스템을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }
}
