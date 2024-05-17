package ch.ilv.voteapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String fname;
    @NotBlank
    @Size(max = 30)
    private String lname;
    private int age = 0;
    private int blk = 0;
    private int lot = 0;
    private boolean migs = false;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
}
