package ch.ilv.voteapp.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UserVoteDto {
    private int userId;
    private int candidateId;
    private int createdBy;
    private Date createdDate;
    private int updatedBy;
    private Date updatedDate;
}
