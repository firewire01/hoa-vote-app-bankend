package ch.ilv.voteapp.repository;

import ch.ilv.voteapp.entity.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalsRepo extends JpaRepository<Goals, Long> {
//    @Query(value = "Select * FROM user_vote join candidate c on candidate_id = c.id where type = :type",nativeQuery = true)
    List<Goals> findAllByCandidateId(Long id);
}
