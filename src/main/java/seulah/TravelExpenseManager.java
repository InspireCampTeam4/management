package seulah;

import seulah.models.Expense;
import seulah.models.Member;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TravelExpenseManager {
    private Map<String, Member> members = new HashMap<>(); // 참가자 정보
    private List<Expense> expenses = new ArrayList<>();    // 지출 내역

    public void run(Scanner scanner) {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMember(scanner);
                    break;
                case 2:
                    addExpense(scanner);
                    break;
                case 3:
                    calculateOverallSettlement();
                    break;
                case 4:
                    viewDetails(scanner);
                    break;
                case 5:
                    deleteMember(scanner);
                    break;
                case 6:
                    deleteExpense(scanner);
                    break;
                case 7:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("\n여행 경비 정산 시스템");
        System.out.println("1. 참가자 등록");
        System.out.println("2. 지출 추가");
        System.out.println("3. 정산 결과");
        System.out.println("4. 내역 조회");
        System.out.println("5. 참가자 삭제");
        System.out.println("6. 내역 삭제");
        System.out.println("7. 종료");
        System.out.print("메뉴를 선택하세요: ");
    }

    private void addMember(Scanner scanner) {
        System.out.print("참가자 이름을 입력하세요: ");
        String name = scanner.nextLine();

        if (members.containsKey(name)) {
            System.out.println(name + "은(는) 이미 등록되어 있습니다.");
            return;
        }

        members.put(name, new Member(name, 0.0));
        System.out.println(name + "이(가) 등록되었습니다.");
    }

    private void addExpense(Scanner scanner) {
        System.out.print("지출자 이름: ");
        String participant = scanner.nextLine();
        if (!members.containsKey(participant)) {
            System.out.println(participant + "은(는) 등록되지 않은 참가자입니다.");
            return;
        }

        System.out.print("지출 항목: ");
        String category = scanner.nextLine();
        System.out.print("지출 금액: ");
        double amount = scanner.nextDouble();
        System.out.print("지출 날짜 (YYYY-MM-DD): ");
        String dateInput = scanner.next();
        LocalDate date = LocalDate.parse(dateInput);

        expenses.add(new Expense(participant, category, amount, date));
        members.get(participant).updateBalance(amount);
        System.out.println(participant + "님의 지출이 추가되었습니다.");
    }

    private void calculateOverallSettlement() {
        // 1. 총 지출 금액 계산
        double totalSpent = members.values().stream()
                .mapToDouble(Member::getBalance)
                .sum();
        double averageSpent = totalSpent / members.size(); // 평균 지출 금액

        // 2. 출력: 총 지출 및 평균 지출 금액
        System.out.println("\n전체 정산 결과:");
        System.out.println("총 지출 금액: " + String.format("%.2f", totalSpent));
        System.out.println("평균 지출 금액: " + String.format("%.2f", averageSpent));

        // 3. 개인별 차이 계산 (돌려받아야 하는 금액 또는 갚아야 하는 금액)
        Map<String, Double> settlements = new HashMap<>();
        for (Member member : members.values()) {
            double difference = member.getBalance() - averageSpent; // 초과/부족 금액
            settlements.put(member.getName(), difference);
        }

        // 4. 초과 금액(돌려받아야 하는 사람)과 부족 금액(갚아야 하는 사람) 리스트 구분
        List<AbstractMap.SimpleEntry<String, Double>> creditors = new ArrayList<>();
        List<AbstractMap.SimpleEntry<String, Double>> debtors = new ArrayList<>();

        settlements.forEach((name, balance) -> {
            if (balance > 0) creditors.add(new AbstractMap.SimpleEntry<>(name, balance)); // 돌려받아야 할 금액
            else if (balance < 0) debtors.add(new AbstractMap.SimpleEntry<>(name, balance)); // 갚아야 할 금액
        });

        // 5. 정산 매칭
        System.out.println("\n정산 결과 :");
        for (AbstractMap.SimpleEntry<String, Double> creditor : creditors) {
            double creditorAmount = creditor.getValue(); // 받을 금액

            // 갚아야 할 사람 리스트 순회
            for (Iterator<AbstractMap.SimpleEntry<String, Double>> it = debtors.iterator(); it.hasNext(); ) {
                AbstractMap.SimpleEntry<String, Double> debtor = it.next();
                double debtorAmount = -debtor.getValue(); // 갚을 금액 (음수라서 양수로 변경)

                // 현재 갚아야 할 금액과 돌려받을 금액 중 작은 금액만큼 정산
                double amountToPay = Math.min(creditorAmount, debtorAmount);
                System.out.println(debtor.getKey() + "님이 " + creditor.getKey() + "님에게 " + String.format("%.2f", amountToPay) + "원을 주셔야합니다.");

                // 금액 조정
                creditorAmount -= amountToPay; // 돌려받을 금액 감소
                debtorAmount -= amountToPay;   // 갚아야 할 금액 감소

                // 갚아야 할 금액이 0이 되면 debtors 리스트에서 제거
                if (debtorAmount == 0) it.remove();
                else debtor.setValue(-debtorAmount); // 갚아야 할 금액 업데이트

                // 돌려받을 금액이 0이 되면 다음 creditor로 넘어감
                if (creditorAmount == 0) break;
            }
            creditor.setValue(creditorAmount); // 남은 돌려받을 금액 업데이트
        }
    }


    private void viewDetails(Scanner scanner) {
        System.out.println("\n1) 개인별 내역 조회");
        System.out.println("2) 항목별 내역 조회");
        System.out.println("3) 날짜별 내역 조회");
        System.out.print("선택하세요: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                viewPersonalSummary(scanner);
                break;
            case 2:
                calculateSettlementByCategory(scanner);
                break;
            case 3:
                viewExpensesByDate(scanner);
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }

    private void viewPersonalSummary(Scanner scanner) {
        System.out.print("조회할 참가자 이름: ");
        String name = scanner.nextLine();

        if (!members.containsKey(name)) {
            System.out.println(name + "은(는) 등록되지 않은 참가자입니다.");
            return;
        }

        System.out.println("\n" + name + "의 지출 내역:");
        expenses.stream()
                .filter(expense -> expense.getParticipant().equals(name))
                .forEach(System.out::println);
    }

    private void calculateSettlementByCategory(Scanner scanner) {
        System.out.print("조회할 항목 입력: ");
        String category = scanner.nextLine();

        List<Expense> filteredExpenses = expenses.stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        if (filteredExpenses.isEmpty()) {
            System.out.println("해당 항목의 지출 내역이 없습니다.");
            return;
        }

        System.out.println("\n항목별 내역 조회:");
        filteredExpenses.forEach(System.out::println);

        double totalCategorySpent = filteredExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
        System.out.println("해당 항목의 총 지출 금액: " + String.format("%.2f", totalCategorySpent));
    }

    private void viewExpensesByDate(Scanner scanner) {
        System.out.print("조회할 날짜 (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput);

        List<Expense> filteredExpenses = expenses.stream()
                .filter(expense -> expense.getDate().equals(date))
                .collect(Collectors.toList());

        if (filteredExpenses.isEmpty()) {
            System.out.println(date + "의 지출 내역이 없습니다.");
            return;
        }

        System.out.println("\n" + date + "의 지출 내역:");
        filteredExpenses.forEach(System.out::println);
    }

    private void deleteMember(Scanner scanner) {
        System.out.print("삭제할 참가자 이름을 입력하세요: ");
        String name = scanner.nextLine();

        if (!members.containsKey(name)) {
            System.out.println(name + "은(는) 등록되지 않은 참가자입니다.");
            return;
        }

        members.remove(name);
        expenses.removeIf(expense -> expense.getParticipant().equals(name));
        System.out.println(name + "이(가) 삭제되었습니다.");
    }

    private void deleteExpense(Scanner scanner) {
        System.out.print("삭제할 지출자 이름: ");
        String participant = scanner.nextLine();
        System.out.print("삭제할 날짜 (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput);

        expenses.removeIf(expense -> expense.getParticipant().equals(participant) && expense.getDate().equals(date));
        System.out.println(participant + "의 " + date + " 내역이 삭제되었습니다.");
    }
}
