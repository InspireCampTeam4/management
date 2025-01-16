package subin.service;

import subin.dto.PodDTO;
import subin.dto.UserDTO;
import subin.repository.TaxiPodRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PodServiceImpl implements PodService {
    TaxiPodRepository taxiPodRepository = new TaxiPodRepository();
    @Override
    public PodDTO createPod(UserDTO userDTO, Date createdAt, String dep, String arv, int memberLimited) {
        int podId = taxiPodRepository.generatePodId();

        PodDTO pod = new PodDTO(
                podId,
                dep,
                arv,
                userDTO,
                new ArrayList<>(), // 초기 멤버 리스트
                createdAt,
                null, // 출발 시각은 null로 초기화
                memberLimited
        );
        pod.getMembers().add(userDTO); // 매니저를 멤버 리스트에 추가
        taxiPodRepository.addPod(pod);
        return pod;
    }

    @Override
    public List<PodDTO> searchPodbyRoute(String dep, String arv) {
        List<PodDTO> allPods = taxiPodRepository.getAllPods();
        return allPods.stream()
                .filter(pod -> pod.getDep().equalsIgnoreCase(dep) && pod.getArv().equalsIgnoreCase(arv))
                .collect(Collectors.toList());
    }
    @Override
    public PodDTO searchPodbyId(int id) {
        List<PodDTO> allPods = taxiPodRepository.getAllPods();

        for (PodDTO pod : allPods) {
            if (pod.getPodId() == id) {
                return pod;
            }
        }
        return null;
    }

    @Override
    public List<PodDTO> searchAll() {
        return taxiPodRepository.getAllPods();
    }

    @Override
    public int joinPod(PodDTO pod, UserDTO user) {
        if (pod.getMembers().size() < pod.getMemberLimited()) {
            pod.getMembers().add(user);  // 사용자 추가
            taxiPodRepository.updatePod(pod);  // 리포지토리에 업데이트
            System.out.println("택시팟에 성공적으로 참여했습니다!");
            return 1; // 성공
        } else {
            System.out.println("해당 택시팟은 인원이 꽉 찼습니다. 참여할 수 없습니다.");
            return -1; // 인원이 꽉 찼음
        }
    }
}
