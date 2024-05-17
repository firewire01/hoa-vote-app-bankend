package ch.ilv.voteapp.service;

import ch.ilv.voteapp.dto.HeaderResponse;
import ch.ilv.voteapp.repository.CandidateRepo;
import ch.ilv.voteapp.repository.UserRepo;
import ch.ilv.voteapp.repository.UserVoteRepo;
import ch.ilv.voteapp.service.interf.HeaderServiceInt;
import org.springframework.stereotype.Service;

@Service
public class HeaderService implements HeaderServiceInt {

    private final UserRepo userRepo;

    private final CandidateRepo candidateRepo;

    private final UserVoteRepo userVoteRepo;

    public HeaderService(UserRepo userRepo, CandidateRepo candidateRepo, UserVoteRepo userVoteRepo) {
        this.userRepo = userRepo;
        this.candidateRepo = candidateRepo;
        this.userVoteRepo = userVoteRepo;
    }

    @Override
    public HeaderResponse getHeaders() {
        HeaderResponse headerResponse = new HeaderResponse();

        headerResponse.setNumberOfActivatedUser(userRepo.count());
        headerResponse.setNumberOfCastVotes(userVoteRepo.count());
        headerResponse.setNumberOfCandidates(candidateRepo.count());
        headerResponse.setNumberOfActivatedUser(userRepo.countActiveUser());

        return headerResponse;
    }
}
