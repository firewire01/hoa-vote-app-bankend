package ch.ilv.voteapp.dto;

import lombok.Data;

@Data
public class PersonDto {
    private Long id;
    private String fname;
    private String lname;
    private int blk;
    private int lot;
    private boolean migs;
    private String email;
    private int age;
}
