package ch.ilv.voteapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class VoteDto {
    private Long id;
    List<Long> candidates;
}
