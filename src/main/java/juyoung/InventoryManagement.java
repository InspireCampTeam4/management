package juyoung;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static juyoung.InventoryUtil.integerHandler;
import static juyoung.InventoryUtil.stringHandler;

public class InventoryManagement {
    HashMap<String, HashSet<Inventory>> categoryMap = new HashMap<>();
    HashMap<Integer, Inventory> inventories = new HashMap<>();

    public void addInventory(Scanner scanner) {
        String category = stringHandler(scanner, "상품의 카테고리를 입력해주세요: ", "카테고리를");
        String name = stringHandler(scanner, "상품의 이름을 입력해주세요: ", "이름을");
        String description = stringHandler(scanner, "상품의 상세정보를 입력해주세요: ", "상세정보를");
        int itemCode = integerHandler(scanner, "상품의 아이템코드를 입력해주세요: ", "코드(숫자)를");
        if (inventories.containsKey(itemCode)) {
            System.out.println("해당 코드의 상품이 존재합니다. 상품 변경을 원할 경우, 삭제 후 시도해주세요.");
            return;
        }
        int quantity = integerHandler(scanner, "상품의 현재 수량을 입력해주세요: ", "수량을");
        double inventoryValuePerUnit = integerHandler(scanner, "상품의 개당 구매 단가를 입력해주세요: ", "개당 구매 단가를");

        Inventory newInventory = new Inventory(category, name, description, itemCode, quantity, inventoryValuePerUnit, 0);
        if (categoryMap.containsKey(category)) {
            categoryMap.get(category).add(newInventory);
        } else {
            categoryMap.put(category, new HashSet<>(List.of(newInventory)));
        }
        inventories.put(itemCode, newInventory);
    }


    public void deleteInventory(Scanner scanner) {
        int itemCode = integerHandler(scanner, "삭제할 상품의 아이템코드를 입력해주세요: ", "코드(숫자)를");
        if (!inventories.containsKey(itemCode)) {
            System.out.println("해당 아이템코드를 가진 상품이 존재하지 않습니다.");
            return;
        }

        Inventory inventory = inventories.get(itemCode);
        String category = inventory.getCategory();
        HashSet<Inventory> inventoryHashSet = categoryMap.get(category);

        inventoryHashSet.remove(inventory);
        if (inventoryHashSet.isEmpty()) categoryMap.remove(category);
        inventories.remove(itemCode);
        System.out.println("삭제가 완료되었습니다.");
    }

    public void inbound(Scanner scanner) {
        int itemCode = integerHandler(scanner, "입고할 상품의 아이템코드를 입력해주세요: ", "코드(숫자)를");
        if (!inventories.containsKey(itemCode)) {
            System.out.println("해당 아이템코드를 가진 상품이 존재하지 않습니다.");
            return;
        }
        int inboundQuantity = integerHandler(scanner, "입고할 상품의 수량을 입력해주세요: ", "수량을");
        if (inboundQuantity < 0) {
            System.out.println("음수는 입력이 불가능합니다. 확인 . 다음에 시도해주세요.");
            return;
        }
        double inboundInventoryValuePerUnit = integerHandler(scanner, "입고할 상품의 개당 구매 단가를 입력해주세요: ", "개당 구매 단가를");


        Inventory inventory = inventories.get(itemCode);
        int newQuantity = inventory.getQuantity() + inboundQuantity;
        double newInventoryValuePerUnit = (inventory.getQuantity() * inventory.getInventoryValuePerUnit() + inboundInventoryValuePerUnit * inboundQuantity) / newQuantity;
        inventory.setQuantity(newQuantity);
        if (!Double.isNaN(newInventoryValuePerUnit)) inventory.setInventoryValuePerUnit(newInventoryValuePerUnit);
        System.out.println("입고가 완료되었습니다.");
    }

    public void outbound(Scanner scanner) {
        int itemCode = integerHandler(scanner, "출고할 상품의 아이템코드를 입력해주세요: ", "코드(숫자)를");
        if (!inventories.containsKey(itemCode)) {
            System.out.println("해당 아이템코드를 가진 상품이 존재하지 않습니다.");
            return;
        }
        int outboundQuantity = integerHandler(scanner, "출고할 상품의 수량을 입력해주세요: ", "수량을");
        Inventory inventory = inventories.get(itemCode);
        if (inventory.getQuantity() < outboundQuantity) {
            System.out.println("현재 재고보다 많은 값을 입력하였습니다. 확인 후 다음에 시도해주세요.");
            return;
        }
        int outboundInventoryValuePerUnit = integerHandler(scanner, "출고할 상품의 개당 판매 단가를 입력해주세요: ", "개당 판매 단가를");

        inventory.setQuantity(inventory.getQuantity() - outboundQuantity);
        inventory.setProfit(inventory.getProfit() + outboundInventoryValuePerUnit * outboundQuantity);
        System.out.println("출고가 완료되었습니다.");
    }

    public void printAllInventory(Scanner scanner) {
        for (Inventory inventory : inventories.values()) {
            System.out.println(inventory);
        }
    }

    public void searchInventory(Scanner scanner) {
        String require = "검색 방법\n" + "1. 코드 검색\n" + "2. 이름 검색\n" + "3. 카테고리 검색\n" + "번호를 입력해 주세요: ";
        while (true) {
            int number = integerHandler(scanner, require, "번호를");
            switch (number) {
                case 1:
                    int scanItemCode = integerHandler(scanner, "찾으실 상품의 아이템코드를 입력해주세요: ", "코드(숫자)를");
                    System.out.println(inventories.containsKey(scanItemCode)
                            ? inventories.get(scanItemCode) : "해당 아이템코드를 가진 상품이 존재하지 않습니다.");
                    return;
                case 2:
                    String scanName = stringHandler(scanner, "찾으실 상품의 이름을 입력해주세요: ", "이름을");
                    List<Inventory> items = inventories.values().stream()
                            .filter(item -> item.getName().equals(scanName))
                            .collect(Collectors.toList());
                    if (items.isEmpty()) {
                        System.out.println("해당 이름를 가진 상품이 존재하지 않습니다.");
                    } else {
                        items.forEach(System.out::println);
                    }
                    return;
                case 3:
                    String scanCategoryName = stringHandler(scanner, "찾으실 상품의 카테고리를 입력해주세요: ", "카테고리를");
                    if (categoryMap.containsKey(scanCategoryName)) {
                        categoryMap.get(scanCategoryName).forEach(System.out::println);
                    } else {
                        System.out.println("해당 카테고리를 가진 상품이 존재하지 않습니다.");
                    }
                    return;
                default:
                    System.out.println("\n올바른 번호(1 - 7)를 입력해주세요.");
            }
        }
    }
}
