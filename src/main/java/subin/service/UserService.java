package subin.service;



import subin.dto.UserDTO;

import java.util.Date;
import java.util.List;

public interface UserService {
    UserDTO createUser (String userId, String pwd);
    List<UserDTO> searchAllUsers ();

    UserDTO findUserById(String managerId);
}
