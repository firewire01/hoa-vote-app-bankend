package ch.ilv.voteapp.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleId implements Serializable {
    private Role role;
    private User user;
}
