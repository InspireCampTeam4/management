package subin;

import subin.dto.PodDTO;
import subin.dto.UserDTO;
import subin.service.PodService;
import subin.service.PodServiceImpl;
import subin.service.UserService;
import subin.service.UserServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TaxiPodController {
    private PodService podService = new PodServiceImpl();
    private UserService userService = new UserServiceImpl();

    // 1. 회원 등록
    public void addUser (Scanner scanner){
        System.out.println("아이디와 패스워드를 입력하세요.");
        System.out.print("아이디: ");
        String userId = scanner.nextLine();
        System.out.print("패스워드: ");
        String pwd = scanner.nextLine();

        UserDTO user = userService.createUser(userId, pwd);
        if (user != null) {
            System.out.println("회원 등록 완료: " + user.getId());
        } else {
            System.out.println("회원 등록 실패!");
        }
    }
    // 2. 택시팟 등록
    public void addPod (Scanner scanner){
        System.out.println("출발지: ");
        String dep = scanner.nextLine();
        System.out.println("목적지: ");
        String arv = scanner.nextLine();
        System.out.println("인원 수: ");
        int num = Integer.parseInt(scanner.nextLine());
        System.out.println("방장 아이디: ");
        String managerId = scanner.nextLine();

        // 매니저를 아이디로 찾기
        UserDTO manager = userService.findUserById(managerId);  // 아이디로 매니저 찾기
        if (manager == null) {
            System.out.println("해당 아이디의 사용자가 존재하지 않습니다.");
            return;
        }
        Date createdAt = new Date();

        PodDTO pod = podService.createPod(manager, createdAt, dep, arv, num);
        System.out.println("택시팟 생성 성공! Pod ID: " + pod.getPodId());

    }
    // 3. 택시팟 검색
    public void searchPod(Scanner scanner){
        System.out.println("출발지를 입력하세요: ");
        String dep = scanner.nextLine();
        System.out.println("목적지를 입력하세요: ");
        String arv = scanner.nextLine();

        List<PodDTO> matchingPods = podService.searchPodbyRoute(dep, arv);

        if (matchingPods.isEmpty()) {
            System.out.println("해당 경로의 택시팟이 없습니다.");
        } else {
            System.out.println("검색 결과");
            System.out.println(String.format("%-5s %-15s %-15s %-10s %-20s %-10s",
                    "No.", "Departure", "Arrival", "Manager", "DepartureTime", "#ofMembers"));
            for (int i = 0; i < matchingPods.size(); i++) {
                PodDTO podDTO = matchingPods.get(i);
                String managerId = podDTO.getManager().getId();
                int numofmem = podDTO.getMembers().size();
                System.out.println(String.format("%-5d %-15s %-15s %-10s %-20s %-10s",
                        podDTO.getPodId(), podDTO.getDep(), podDTO.getArv(), managerId,
                        podDTO.getDepartedAt(), numofmem + "/" + podDTO.getMemberLimited()));
            }

        }

    }
    // 4. 택시팟 참여
    public void joinPod(Scanner scanner){
        System.out.println("참여하고 싶은 택시의 아이디를 입력하세요: ");
        int taxiId = scanner.nextInt();
        scanner.nextLine();  // 버퍼에 남은 엔터를 소비하는 코드
        System.out.println("본인의 아이디를 입력하세요: ");
        String myId = scanner.nextLine();
        PodDTO pod = podService.searchPodbyId(taxiId);
        UserDTO currentUser = userService.findUserById(myId); // 현재 사용자를 가져오는 메서드
        if (pod == null) {
            System.out.println("해당 ID를 가진 택시팟이 없습니다.");
            return;
        }
        if (currentUser == null) {
            System.out.println("해당 ID를 가진 회원이 없습니다.");
            return;
        }

        if (pod.getMembers().size() < pod.getMemberLimited()) {
            podService.joinPod(pod, currentUser);
            System.out.println("택시팟에 성공적으로 참여했습니다!");
        } else {
            System.out.println("해당 택시팟은 인원이 꽉 찼습니다. 참여할 수 없습니다.");
        }


    }
    // 5. 택시팟 전체 조회
    public void searchAllPods(){
        List<PodDTO> pods = podService.searchAll();
        System.out.println(String.format("%-5s %-15s %-15s %-10s %-20s %-10s",
                "No.", "Departure", "Arrival", "Manager", "DepartureTime", "#ofMembers"));
        for (int i = 0; i < pods.size(); i++) {
            PodDTO podDTO = pods.get(i);
            String managerId = podDTO.getManager().getId();
            int numofmem = podDTO.getMembers().size();
            System.out.println(String.format("%-5d %-15s %-15s %-10s %-20s %-10s",
                    podDTO.getPodId(), podDTO.getDep(), podDTO.getArv(), managerId,
                    podDTO.getDepartedAt(), numofmem + "/" + podDTO.getMemberLimited()));
        }
    }
    // 6. 회원 전체 조회
    public void searchAllUsers(){
        List<UserDTO> users = userService.searchAllUsers();
        System.out.println("No.\tUserId");
        for (int i = 0; i < users.size(); i++) {
            UserDTO user = users.get(i);
            System.out.println(i + "\t" + user.getId());
        }
    }

}
