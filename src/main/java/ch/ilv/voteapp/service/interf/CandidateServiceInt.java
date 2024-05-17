package ch.ilv.voteapp.service.interf;

import ch.ilv.voteapp.base.MessageResponse;
import ch.ilv.voteapp.dto.CandidateDto;
import ch.ilv.voteapp.dto.FileDTO;
import ch.ilv.voteapp.dto.ImagePicture;
import ch.ilv.voteapp.entity.CandidateType;

import java.util.List;

public interface CandidateServiceInt {

    //add candidate
    public CandidateDto addCandidate(CandidateDto candidateDto);
    //get candidate
    public CandidateDto getCandidate(Long id);
    //list of candidate
    public List<CandidateDto> getCandidates(CandidateType candidateType);

    void updateCandidate(CandidateDto candidateDto, Long id);

    MessageResponse deleteCandidate(Long id);

    void addProfilepic(Long id, FileDTO fileDTO);

    ImagePicture getProfilePic(Long id);

    List<CandidateDto> getAllVoter(Long id);
}
