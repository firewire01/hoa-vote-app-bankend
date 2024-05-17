package ch.ilv.voteapp.repository;

import ch.ilv.voteapp.entity.Candidate;
import ch.ilv.voteapp.entity.CandidateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {

    List<Candidate> findByType(String type);

}
