package ch.ilv.voteapp.service.interf;

import ch.ilv.voteapp.base.MessageResponse;
import ch.ilv.voteapp.dto.*;
import ch.ilv.voteapp.entity.CandidateType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VoterServiceInt {

    //add voters by one
    public void addVoter(VoterDto voterDto);
    //add voters by many
    public void addVoter(List<VoterDto> voterDto);
    //vote
    MessageResponse vote(VoteDto voteDto);

    //list of vote
    public Long getCountCandidateByType(CandidateType candidateType);
    public Long getCountCandidateById(Long id);
    public List<UserVoteDto> getCandidateByVoter(Long id);
    public List<UserVoteDto> getCandidateByType(CandidateType candidateType);

    VoterDto getVoter(Long id);

    VoterDto updateVoter(VoterDto voterDto, Long id);

    MessageResponse deleteVoter(Long id);

    MessageResponse updatePass(UpdatePassDto updatePassDto, PasswordEncoder passwordEncoder);

    MessageResponse checkUser(String username);

    MessageResponse resetUser(Long id);

    MessageResponse bulkAddVoter(MultipartFile file);

    List<VoterDto> getAll();
    //votes by candidate
    //votes by voter
}
