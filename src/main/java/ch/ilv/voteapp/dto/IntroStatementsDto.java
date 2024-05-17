package ch.ilv.voteapp.dto;

import lombok.Data;

@Data
public class IntroStatementsDto {
    private Long id;
    private String description;
    private Long candidateId;
}
