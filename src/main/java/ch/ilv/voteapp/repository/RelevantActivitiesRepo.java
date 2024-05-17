package ch.ilv.voteapp.repository;

import ch.ilv.voteapp.entity.RelevantActivities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RelevantActivitiesRepo extends JpaRepository<RelevantActivities, Long> {
    List<RelevantActivities> findAllByCandidateId(Long id);
}
