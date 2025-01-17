package sunwoo.util;

import java.util.Scanner;

public class InputManager {
    private Scanner scanner;

    public InputManager(Scanner scanner) {
        scanner = scanner;
    }

    // 입력값을 읽고 "exit" 확인
    public String nextLine() {
        String input = scanner.nextLine();
        if ("exit".equalsIgnoreCase(input.trim())) {
            System.out.println("프로그램을 종료합니다.");
            System.exit(0); // 프로그램 종료
        }
        return input;
    }
}