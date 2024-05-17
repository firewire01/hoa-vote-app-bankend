package ch.ilv.voteapp.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VoterDto extends PersonDto{
    private boolean directMember;
    private String status;
    private String username;
    private String tempPassword;
    private List<RoleDto> roles;
    private Date activatedDate;
    private Date votedDate;
    private Date lastLogin;
}
