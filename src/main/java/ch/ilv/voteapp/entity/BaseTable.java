package ch.ilv.voteapp.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BaseTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
