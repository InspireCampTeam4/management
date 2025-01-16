package jiwon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString

public class Menu {
    private String menuId;       // 메뉴 ID
    private String menuName;     // 메뉴 이름
    private String menuType;     // 메뉴 타입
    private String description;  // 메뉴 설명 (간략한 내용)
    private int price;           // 가격
    private int calorie;         // 칼로리
}
