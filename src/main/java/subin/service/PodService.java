package subin.service;


import subin.dto.PodDTO;
import subin.dto.UserDTO;

import java.util.Date;
import java.util.List;

public interface PodService {
    PodDTO createPod (UserDTO userDTO, Date date, String dst, String arv, int memberLimited);
    List<PodDTO> searchPodbyRoute(String dst, String arv);

    PodDTO searchPodbyId(int id);

    List<PodDTO> searchAll ();

    int joinPod(PodDTO pod, UserDTO user);
}
