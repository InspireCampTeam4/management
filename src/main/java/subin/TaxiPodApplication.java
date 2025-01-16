package subin;

import java.util.Scanner;

public class TaxiPodApplication {
    public static void printMenu() {
        System.out.println("\n++++ 택시팟 서비스(q를 눌러 종료하십시오) +++ ");
        System.out.println("1. 회원 등록");
        System.out.println("2. 택시팟 등록");
        System.out.println("3. 택시팟 검색");
        System.out.println("4. 택시팟 참여");
        System.out.println("5. 택시팟 전체 조회");
        System.out.println("6. 회원 전체 조회");
        System.out.println("7. 종료");
        System.out.print("메뉴를 선택하세요: ");
    }

    public static void main(String[] args) {

        TaxiPodController taxipod = new TaxiPodController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    taxipod.addUser(scanner);
                    break;
                case 2:
                    taxipod.addPod(scanner);
                    break;
                case 3:
                    taxipod.searchPod(scanner);
                    break;
                case 4:
                    taxipod.joinPod(scanner);
                    break;
                case 5:
                    taxipod.searchAllPods();
                    break;
                case 6:
                    taxipod.searchAllUsers();
                    break;
                case 7:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }

        }
    }
}
