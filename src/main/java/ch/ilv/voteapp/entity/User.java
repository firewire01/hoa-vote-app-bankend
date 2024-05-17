package ch.ilv.voteapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user_hoa")
public class User extends Person {

    private boolean directMember;
    private Status status;
    private String password;
    private String tempPassword;
    @Column(name = "activated_date")
    private Date activatedDate;
    @Column(name = "voted_date")
    private Date votedDate;
    @Column(name = "last_login")
    private Date lastLogin;

    @NotBlank
    @Size(max = 20)
    private String username;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserVote> userVotes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
