package ch.ilv.voteapp.repository;

import ch.ilv.voteapp.entity.UserRole;
import ch.ilv.voteapp.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findByUserId(Long id);
}
