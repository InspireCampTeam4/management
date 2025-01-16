import seulah.TravelExpenseManager; // seulah 패키지의 클래스 임포트
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner 객체 생성 (사용자 입력 처리)
        Scanner scanner = new Scanner(System.in);

        // TravelExpenseManager 객체 생성
        TravelExpenseManager manager = new TravelExpenseManager();

        // TravelExpenseManager 실행
        manager.run(scanner);

        // Scanner 리소스 해제
        scanner.close();
    }
}