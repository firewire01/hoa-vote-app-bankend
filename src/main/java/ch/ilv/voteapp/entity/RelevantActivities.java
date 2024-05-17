package ch.ilv.voteapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="relevant_activities")
public class RelevantActivities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate candidate;

}
