package sunwoo.util;

import java.util.Scanner;

public class InputHandler {
    public static int getIntValue(Scanner scanner){

        while(true){
            String value = scanner.nextLine().trim();
            if (value.matches("-?\\d+")) { // 정수 검증 (-?는 음수 허용)
             return Integer.parseInt(value);
            } else {
                LogHandler.warn("밴드 가입 제한 인원 수는 정수로 입력되어야 합니다.");
            }
        }

    }


}
