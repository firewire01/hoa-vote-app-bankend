package ch.ilv.voteapp.repository;

import ch.ilv.voteapp.entity.IntroStatements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface IntroStatementsRepo extends JpaRepository<IntroStatements, Long> {
    List<IntroStatements> findAllByCandidateId(Long id);
}
