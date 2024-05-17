package ch.ilv.voteapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeaderResponse {

    private long numberOfUsers;
    private long numberOfActivatedUser;
    private long numberOfCastVotes;
    private long numberOfCandidates;
}
