package ch.ilv.voteapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "candidate")
public class Candidate extends Person{

    private boolean violation = false;
    private boolean incumbent = false;
    private int siblings = 0;
    private int yearsInMarried = 0;
    private int yearsInHoa = 0;
    private String facebook;
    private String linkedin;
    @NotBlank
    private String type;
    private String industry;
    private String mname;
    private Date lastVoted;

//    @Type(type="org.hibernate.type.BinaryType"
    private byte[] profilePic;

    @Column(name = "contact_number")
    private String contactNumber;

    private String status;
    private String base64pic;
    @Column(name = "current_job")
    private String currentJob;

    @Column(name = "last_position")
    private String lastPosition;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private List<UserVote> userVotes;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private List<IntroStatements> introStatements;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private List<Goals> goals;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private List<RelevantActivities> relevantActivities;

}
