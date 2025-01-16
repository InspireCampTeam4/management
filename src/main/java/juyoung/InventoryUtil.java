package juyoung;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class InventoryUtil {
    public static String stringHandler(Scanner scanner, String require, String error) {
        System.out.print(require);
        return Stream.generate(() -> {
                    String input = scanner.nextLine().trim();
                    if (input.isBlank()) {
                        System.out.print("올바른 " + error + " 입력해주세요: ");
                        return null;
                    }
                    return input;
                })
                .filter(Objects::nonNull) // null 제거
                .findFirst() // 첫번째에 유효 값이 있으면 종료 (Terminal Operation, 스트림의 흐름을 중단)
                .orElse(""); // 예외 처리
    }

    public static int integerHandler(Scanner scanner, String require, String error) {
        System.out.print(require);
        return Stream.generate(() -> {
                    try {
                        int input = scanner.nextInt();
                        scanner.nextLine();
                        return input;
                    } catch (InputMismatchException e) {
                        System.out.print("올바른 " + error + " 입력해주세요: ");
                        scanner.nextLine();
                        return null; // 잘못된 입력은 null 로 간주
                    }
                })
                .filter(Objects::nonNull) // null 제거
                .findFirst() // 첫번째에 유효 값이 있으면 종료 (Terminal Operation, 스트림의 흐름을 중단)
                .orElse(0); // 예외 처리
    }
}
