package subin.service;

import subin.dto.UserDTO;
import subin.repository.TaxiPodRepository;

import java.util.List;

public class UserServiceImpl implements UserService{
    TaxiPodRepository taxiPodRepository = new TaxiPodRepository();

    @Override
    public UserDTO createUser(String userId, String pwd) {
        UserDTO newUser = new UserDTO(userId, pwd);
        boolean isSaved = taxiPodRepository.addUser(newUser);
        if (isSaved) {
            return newUser;
        } else {
            return null;
        }
    }
    // 아이디로 UserDTO 찾기
    public UserDTO findUserById(String userId) {
        List<UserDTO> users = taxiPodRepository.getAllUsers();
        for (UserDTO user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    @Override
    public List<UserDTO> searchAllUsers() {
        return taxiPodRepository.getAllUsers();
    }

}
