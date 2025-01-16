package subin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PodDTO {
    private int podId;
    private String dep;
    private String arv;
    private UserDTO manager;
    private List<UserDTO> members;
    private Date createdAt;
    private Date departedAt;
    private int memberLimited;

}
