package jiwon;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

public class MenuManagement {
    // 메뉴 정보
    private List<Menu> menus = new ArrayList<>();

    // menuId => 메뉴 등록 시 동일한 메뉴가 존재하는지 확인
    private Set<String> menuIdSet = new HashSet<>();

    // 메뉴 등록
    public void addMenu(Scanner scanner) {
        // 메뉴 id 입력
        String menuId;
        while (true) {
            System.out.print("menu id: ");
            menuId = scanner.nextLine();

            // 기존 메뉴 id와 겹치는 경우
            if (menuIdSet.contains(menuId)) {
                System.out.printf("menu id %s과 동일한 메뉴가 이미 등록되어 있습니다. 다시 입력하세요.\n", menuId);
            } else {
                break;
            }
        }

        // 메뉴 이름 입력
        System.out.print("메뉴 이름: ");
        String menuName = scanner.nextLine();

        // 메뉴 타입은 drink와 food 중 하나만 입력 가능
        String menuType;
        while (true) {
            System.out.print("메뉴 타입 (drink/food): ");
            menuType = scanner.nextLine().toLowerCase();

            // 잘못된 타입 입력 시 다시 입력
            if (!menuType.equals("drink") && !menuType.equals("food")) {
                System.out.println("메뉴 타입은 'drink' 또는 'food'만 가능합니다. 다시 입력하세요.");
            } else {
                break;
            }
        }

        // 메뉴 설명 입력
        System.out.print("메뉴 설명: ");
        String description = scanner.nextLine();

        // 가격 입력 (숫자만 허용)
        int price = 0;
        while (true) {
            System.out.print("가격(원): ");
            try {
                price = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("가격은 숫자로 입력해야 합니다. 다시 입력하세요.");
            }
        }

        // 열량 입력 (숫자만 허용)
        int calorie = 0;
        while (true) {
            System.out.print("열량: ");
            try {
                calorie = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("열량은 숫자로 입력해야 합니다. 다시 입력하세요.");
            }
        }

        // 메뉴 객체 생성 및 리스트에 추가
        Menu menu = new Menu(menuId, menuName, menuType, description, price, calorie);
        menus.add(menu);
        menuIdSet.add(menu.getMenuId()); // menuIdSet에 추가
        System.out.println("정상적으로 등록되었습니다." + menu);
    }

    // 초기 더미 데이터 추가
    public void addDummyData() {
        // 더미 데이터를 menus 리스트에 추가
        menus.add(new Menu("1", "아메리카노", "drink", "시원한 아메리카노", 4500, 5));
        menus.add(new Menu("2", "카페라떼", "drink", "부드러운 카페라떼", 5000, 150));
        menus.add(new Menu("3", "치즈케이크", "food", "달콤한 치즈케이크", 6000, 350));
        menus.add(new Menu("4", "샌드위치", "food", "든든한 샌드위치", 7000, 400));
        menus.add(new Menu("5", "콜드브루", "drink", "진한 콜드브루", 5500, 10));

        // menuIdSet에 더미 데이터의 id 추가
        for (Menu menu : menus) {
            menuIdSet.add(menu.getMenuId());
        }
        System.out.println("더미 데이터가 추가되었습니다.");
    }

    // 전체 메뉴 목록 조회 (정렬 방식 포함)
    public void listMenus(int sortOption, String filterType) {
        // 새로운 리스트 생성 (필터링된 메뉴를 담기 위해)
        List<Menu> filteredMenus = new ArrayList<>();

        // 필터링 (음료/음식 또는 전체)
        for (Menu menu : menus) {
            if (filterType == null || menu.getMenuType().equals(filterType)) {
                filteredMenus.add(menu);
            }
        }

        // 필터링 결과가 없는 경우
        if (filteredMenus.isEmpty()) {
            System.out.println("등록된 메뉴가 없습니다.");
            return;
        }

        // 정렬
        switch (sortOption) {
            case 1: // 높은 가격순
                filteredMenus.sort(Comparator.comparingInt(Menu::getPrice).reversed());
                break;
            case 2: // 낮은 가격순
                filteredMenus.sort(Comparator.comparingInt(Menu::getPrice));
                break;
            case 3: // 고칼로리순
                filteredMenus.sort(Comparator.comparingInt(Menu::getCalorie).reversed());
                break;
            case 4: // 저칼로리순
                filteredMenus.sort(Comparator.comparingInt(Menu::getCalorie));
                break;
            case 5: // 카테고리별
                filteredMenus.sort(Comparator.comparing(Menu::getMenuType));
                break;
            default:
                System.out.println("잘못된 정렬 옵션입니다.");
                return;
        }

        // 정렬된 메뉴 출력
        filteredMenus.forEach(System.out::println);
    }

    // 메뉴 검색 => 메뉴 이름에 keyword가 포함되어 있는지 검색
    public void searchMenus(Scanner scanner) {
        // 검색어 입력
        System.out.print("검색어를 입력하세요: ");
        String keyword = scanner.nextLine();
        keyword = keyword.trim().toLowerCase(); // 검색어 전처리

        // 검색 결과 출력
        boolean found = false;
        for (Menu menu : menus) {
            if (menu.getMenuName().trim().toLowerCase().contains(keyword)) {
                System.out.println(menu);
                found = true;
            }
        }

        // 검색 조회 결과가 없을 때
        if (!found) {
            System.out.printf("%s를 포함하는 메뉴가 없습니다.\n", keyword);
        }
    }

    // 메뉴 삭제
    public void deleteMenu(Scanner scanner) {
        // 삭제할 메뉴의 ID를 입력
        System.out.print("삭제할 메뉴의 id를 입력하세요: ");
        String menuId = scanner.nextLine();

        // 메뉴 삭제
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            if (menu.getMenuId().equals(menuId)) {
                iterator.remove(); // 리스트에서 삭제
                menuIdSet.remove(menuId); // menuIdSet에서도 삭제
                System.out.printf("menu id가 %s인 메뉴를 삭제했습니다.\n", menuId);
                return;
            }
        }

        // 입력한 menu id와 일치하는 값이 없을 경우
        System.out.printf("menu id가 %s와 일치하는 메뉴가 없습니다.\n", menuId);
    }
}