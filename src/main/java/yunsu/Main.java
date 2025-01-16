package yunsu;

import java.util.Scanner;

public class    Main {
    public static void printMenu() {
        System.out.println("\n성적 관리 시스템");
        System.out.println("1. 과목 추가");
        System.out.println("2. 과목 검색");
        System.out.println("3. 과목 삭제");
        System.out.println("4. 과목 목록");
        System.out.println("5. 종료");
        System.out.print("메뉴를 선택하세요: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SubjectManager subjectManager = new SubjectManager();

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1: {
                    // 과목 추가
                    System.out.print("과목 이름을 입력하세요: ");
                    String name = scanner.nextLine();
                    System.out.print("이수 학점을 입력하세요: ");
                    int credit = scanner.nextInt();
                    System.out.print("성적을 입력하세요 (예: 4.0): ");
                    double grade = scanner.nextDouble();
                    scanner.nextLine(); // 버퍼 비우기
                    subjectManager.addSubject(name, credit, grade);
                    break;
                }
                case 2: {
                    // 과목 검색
                    System.out.print("검색할 과목 이름을 입력하세요: ");
                    String name = scanner.nextLine();
                    subjectManager.searchSubject(name);
                    break;
                }
                case 3: {
                    // 과목 삭제
                    System.out.print("삭제할 과목 이름을 입력하세요: ");
                    String name = scanner.nextLine();
                    subjectManager.deleteSubject(name);
                    break;
                }
                case 4: {
                    // 과목 목록
                    subjectManager.displaySubjects();
                    break;
                }
                case 5: {
                    // 종료
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                }
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
            }
        }
    }
}
