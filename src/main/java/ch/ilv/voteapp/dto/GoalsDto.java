package ch.ilv.voteapp.dto;

import lombok.Data;

@Data
public class GoalsDto {
    private Long id;
    private String description;
    private Long candidateId;
}
