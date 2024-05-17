package ch.ilv.voteapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name ="user_vote")
@IdClass(UserVoteId.class)
public class UserVote {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate candidate;

    private Long createdBy;
    private Date createdDate;
    private Long updatedBy;
    private Date updatedDate;
}
