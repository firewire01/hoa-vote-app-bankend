package ch.ilv.voteapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class VoteCandidateDto {
    private Long voterId;
    private List<UserVoteDto> votes;
}
