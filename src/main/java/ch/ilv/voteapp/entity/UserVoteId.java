package ch.ilv.voteapp.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVoteId implements Serializable {
    private Candidate candidate;
    private User user;
}
