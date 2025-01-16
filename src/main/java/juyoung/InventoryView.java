package juyoung;

import java.util.Scanner;

import static juyoung.InventoryUtil.integerHandler;

public class InventoryView {
    private static void printInventoryMenu() {
        System.out.println("\n재고 관리 시스템");
        System.out.println("1. 상품 등록");
        System.out.println("2. 상품 삭제");
        System.out.println("3. 입고");
        System.out.println("4. 출고");
        System.out.println("5. 전체 재고 출력");
        System.out.println("6. 재고 검색");
        System.out.println("7. 종료");
    }

    public static void enter(Scanner scanner) {
        InventoryManagement inventory = new InventoryManagement();

        while (true) {
            printInventoryMenu();
            int choice = integerHandler(scanner, "메뉴를 선택해주세요: ", "번호를");
            switch (choice) {
                case 1:
                    inventory.addInventory(scanner);
                    break;
                case 2:
                    inventory.deleteInventory(scanner);
                    break;
                case 3:
                    inventory.inbound(scanner);
                    break;
                case 4:
                    inventory.outbound(scanner);
                    break;
                case 5:
                    inventory.printAllInventory(scanner);
                    break;
                case 6:
                    inventory.searchInventory(scanner);
                    break;
                case 7:
                    System.out.println("재고 관리 시스템을 종료합니다.");
                    return;
                default:
                    System.out.println("\n올바른 번호(1 - 7)를 입력해주세요.");
            }
        }
    }
}
