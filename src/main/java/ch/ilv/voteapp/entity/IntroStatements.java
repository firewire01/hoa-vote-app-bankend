package ch.ilv.voteapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="intro_statements")
public class IntroStatements {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate candidate;

}
