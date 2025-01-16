import juyoung.InventoryView;
import phonebook.controller.PhoneBookController;
import phonebook.service.PhoneBookService;
import phonebook.view.PhoneBookView;
import minju.MovieManagement;
import subin.TaxiPodApplication;
import sungyeop.BoardEntry;
import yunsu.*;
import seulah.TravelExpenseManager;
import minseok.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void printApplicationMenu() {
        System.out.println("\n: 사용할 응용프로그램을 선택하세요.");
        System.out.println("1. 이슬아: 여행 경비 정산 시스템");
        System.out.println("2. 이정빈: PhoneBook");
        System.out.println("3. 정민석: 위시리스트 관리 시스템");
        System.out.println("4. 정선우: 밴드(음악) 활동 관리 시스템");
        System.out.println("5. 정성엽: 게시판");
        System.out.println("6. 정주영: 재고 관리 시스템");
        System.out.println("7. 조수빈: 택시팟 관리 시스템");
        System.out.println("8. 조윤수: 성적 관리 시스템");
        System.out.println("9. 채민주: 영화 관리 시스템");
        System.out.println("10. 채지원: 카페 메뉴 관리 시스템");
        System.out.println("11. 종료");
        System.out.print("메뉴를 선택하세요: ");
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            printApplicationMenu();
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = 0;
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    TravelExpenseManager manager = new TravelExpenseManager();
                    manager.run(scanner);
                    break;
                case 2:
                    PhoneBookService phoneBookService = new PhoneBookService();
                    PhoneBookView phoneBookView = new PhoneBookView();
                    PhoneBookController phoneBookController = new PhoneBookController(phoneBookService, phoneBookView);
                    phoneBookController.run();
                    break;
                case 3:
                	WishlistController wishlist = new WishlistController();
                	wishlist.run();
                    break;
                case 4:
                    break;
                case 5:
                    BoardEntry boardEntry = new BoardEntry();
                    boardEntry.run();
                    break;
                case 6:
                    InventoryView.enter(scanner);
                    break;
                case 7:
                    TaxiPodApplication.main(null);
                    break;
                case 8:
                    SubjectManager subjectManager = new SubjectManager();
                    subjectManager.runMenu(scanner);

                    break;
                case 9:
                    MovieManagement movieManagement = new MovieManagement();
                    movieManagement.runMenu(scanner);
                    break;
                case 10:
                    break;
                case 11:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}