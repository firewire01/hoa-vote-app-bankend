package ch.ilv.voteapp.repository;

import ch.ilv.voteapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByBlkAndLot(int blk, int lot);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT count(*) from user_hoa where activated_date IS NOT NULL", nativeQuery = true)
    Long countActiveUser();

    @Query(value = "SELECT count(*) from user_hoa where password not null", nativeQuery = true)
    Long checkIfBlockAndUserExist();
}
