package subin.repository;

import subin.dto.PodDTO;
import subin.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class TaxiPodRepository {

    private static TaxiPodRepository instance; // Singleton instance

    private List<UserDTO> users = new ArrayList<>();
    private List<PodDTO> pods = new ArrayList<>();

    // Singleton 패턴을 위한 private 생성자
    public TaxiPodRepository() {}

    public int generatePodId() {
        return pods.size() + 1; // 등록된 팟 수 + 1
    }
    // 회원 추가
    public boolean addUser(UserDTO user) { return users.add(user);}

    // 택시팟 추가
    public void addPod(PodDTO pod) { pods.add(pod); }

    // 회원 전체 조회
    public List<UserDTO> getAllUsers() {
        return users;
    }

    // 택시팟 전체 조회
    public List<PodDTO> getAllPods() {
        return pods;
    }
    public void updatePod(PodDTO pod) {
        // 여기서 podId에 해당하는 pod를 찾고, 수정한 pod로 덮어 씌웁니다.
        pods.stream()
                .filter(existingPod -> existingPod.getPodId() == pod.getPodId())
                .findFirst()
                .ifPresent(existingPod -> {
                    // 수정된 내용을 반영합니다
                    existingPod.setMembers(pod.getMembers());
                });
    }
}
