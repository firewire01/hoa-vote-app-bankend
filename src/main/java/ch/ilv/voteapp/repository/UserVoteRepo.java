package ch.ilv.voteapp.repository;

import ch.ilv.voteapp.entity.CandidateType;
import ch.ilv.voteapp.entity.UserVote;
import ch.ilv.voteapp.entity.UserVoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserVoteRepo extends JpaRepository<UserVote, UserVoteId> {

    @Query(value = "Select count(*) FROM user_vote join candidate c on candidate_id = c.id where type = :type",nativeQuery = true)
    Long countByCandidateType(@Param("type") String candidateType);

    Long countByCandidateId(Long id);

    Long countByUserId(Long id);

    List<UserVote> findByUserId(Long id);

    @Query(value = "Select * FROM user_vote join candidate c on candidate_id = c.id where type = :type",nativeQuery = true)
    List<UserVote> findByCandidateAndType(@Param("type") String candidateType);

    @Query(value = "Delete FROM user_vote WHERE user_id = :id ",nativeQuery = true)
    void deleteAllByUserId(@Param("id") Long id);
}
