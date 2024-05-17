package ch.ilv.voteapp.service;

import ch.ilv.voteapp.base.MessageResponse;
import ch.ilv.voteapp.dto.*;
import ch.ilv.voteapp.entity.*;
import ch.ilv.voteapp.mapper.CandidateMapper;
import ch.ilv.voteapp.repository.*;
import ch.ilv.voteapp.service.interf.CandidateServiceInt;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CandidateService implements CandidateServiceInt {

    private final CandidateRepo candidateRepo;

    private final GoalsRepo goalsRepo;

    private final IntroStatementsRepo introStatementsRepo;

    private final RelevantActivitiesRepo relevantActivitiesRepo;

    private final UserVoteRepo userVoteRepo;

    private final UserRepo userRepo;

    @Autowired
    public CandidateService(CandidateRepo candidateRepo, GoalsRepo goalsRepo, IntroStatementsRepo introStatementsRepo, RelevantActivitiesRepo relevantActivitiesRepo, UserVoteRepo userVoteRepo, UserRepo userRepo) {
        this.candidateRepo = candidateRepo;
        this.goalsRepo = goalsRepo;
        this.introStatementsRepo = introStatementsRepo;
        this.relevantActivitiesRepo = relevantActivitiesRepo;
        this.userVoteRepo = userVoteRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    @Override
    public CandidateDto addCandidate(CandidateDto candidateDto) {
        Candidate candidate = CandidateMapper.INSTANCE.dtoToEnity(candidateDto);
        candidate.setId(null);
        candidate.setStatus(Status.ACTIVE.toString());
        candidate.setType(candidateDto.getCandidateType().toString());

        if(candidateDto.getFileDTO() != null && StringUtils.isNotBlank(candidateDto.getFileDTO().getBase64())){
            candidate.setBase64pic(candidateDto.getFileDTO().getBase64());
        }

        candidate = candidateRepo.save(candidate);
        addGoals(candidate, candidateDto.getGoals());
        addRelevantActivities(candidate, candidateDto.getRelevantActivities());
        addIntroStatements(candidate, candidateDto.getIntroStatements());
        candidateDto.setId(candidate.getId());
        return candidateDto;
    }

    @Override
    public void addProfilepic(Long id, FileDTO fileDTO) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        addImage(candidate, fileDTO);

    }
    private void addImage(Candidate candidate, FileDTO fileDTO) {
        if(fileDTO != null && StringUtils.isNotBlank(fileDTO.getBase64())){
            try {
                saveProfilePic(candidate, fileDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void saveProfilePic(Candidate candidate, FileDTO profilePic) throws IOException {
        if(candidate != null){
            candidate.setBase64pic (profilePic.getBase64());
            candidateRepo.save(candidate);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ImagePicture getProfilePic(Long id) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        if(candidate != null){
            return new ImagePicture(candidate.getBase64pic());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<CandidateDto> getAllVoter(Long id) {

        List<CandidateDto> list = new ArrayList<>();

        userVoteRepo.findByUserId(id).forEach(userVote -> {
            list.add(getCandidate(userVote.getCandidate().getId()));
        });

        return list;
    }

    private void addGoals(Candidate candidate, List<GoalsDto> gaolsDto) {

        List<Goals> list = gaolsDto.stream().map(goalDto  -> {
            Goals goals = new Goals();

            goals.setCandidate(candidate);
            goals.setDescription(goalDto.getDescription());

            return goals;

        }).collect(Collectors.toList());

        goalsRepo.saveAll(list);
    }

    private void addRelevantActivities(Candidate candidate, List<RelevantActivitiesDto> activitiesDtos) {
        List<RelevantActivities> list = activitiesDtos.stream().map(activitiesDto -> {
            RelevantActivities relevantActivities = new RelevantActivities();

            relevantActivities.setCandidate(candidate);
            relevantActivities.setDescription(activitiesDto.getDescription());

            return relevantActivities;
        }).collect(Collectors.toList());

        relevantActivitiesRepo.saveAll(list);
    }

    private void addIntroStatements(Candidate candidate, List<IntroStatementsDto> introStatementsDtos) {
        List<IntroStatements> list = introStatementsDtos.stream().map(introStatementsDto -> {
            IntroStatements introStatements = new IntroStatements();

            introStatements.setCandidate(candidate);
            introStatements.setDescription(introStatementsDto.getDescription());

            return introStatements;
        }).collect(Collectors.toList());

        introStatementsRepo.saveAll(list);
    }

    @Override
    public CandidateDto getCandidate(Long id) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);

//        log.info(ToStringBuilder.reflectionToString(candidate, ToStringStyle.JSON_STYLE));

        CandidateDto candidateDto = CandidateMapper.INSTANCE.entityToDto(
                candidate);

        if(candidate != null && StringUtils.isNotBlank(candidate.getType()))

          candidateDto.setCandidateType(CandidateType.valueOf(candidate.getType()));
          candidateDto.setCurrentJob(candidate.getCurrentJob());

          FileDTO fileDTO = new FileDTO();

          fileDTO.setBase64(candidate.getBase64pic());
        candidateDto.setFileDTO(fileDTO);

        Long count = userVoteRepo.countByCandidateId(id);
        if(count != null){
            candidateDto.setCanidateVote(count);
        }

        BigDecimal value = new BigDecimal(count).divide(new BigDecimal(userRepo.countActiveUser()),
                4, RoundingMode.HALF_UP).scaleByPowerOfTen(2);

        double total = count.doubleValue()/userRepo.countActiveUser().doubleValue();

        candidateDto.setPercentile(total * 100.00);

        candidateDto.setGoals(goalsRepo.findAllByCandidateId(id).stream()
                .map(goals -> {
                    GoalsDto goalsDto = new GoalsDto();

                    goalsDto.setCandidateId(goals.getCandidate().getId());
                    goalsDto.setDescription(goals.getDescription());
                    goalsDto.setId(goals.getId());

                    return goalsDto;
                }).collect(Collectors.toList()));

        candidateDto.setIntroStatements(introStatementsRepo.findAllByCandidateId(id).stream()
                .map(entity -> {
                    IntroStatementsDto dto = new IntroStatementsDto();

                    dto.setCandidateId(entity.getCandidate().getId());
                    dto.setDescription(entity.getDescription());
                    dto.setId(entity.getId());

                    return dto;
                }).collect(Collectors.toList()));

        candidateDto.setRelevantActivities(relevantActivitiesRepo.findAllByCandidateId(id).stream()
                .map(entity -> {
                    RelevantActivitiesDto dto = new RelevantActivitiesDto();

                    dto.setCandidateId(entity.getCandidate().getId());
                    dto.setDescription(entity.getDescription());
                    dto.setId(entity.getId());

                    return dto;
                }).collect(Collectors.toList()));

        return candidateDto;
    }

    @Override
    public List<CandidateDto> getCandidates(CandidateType candidateType) {
        if(candidateType == null){
            return candidateRepo.findAll().stream().map(candidate ->
                getCandidate(candidate.getId())
            ).collect(Collectors.toList());
        }else{
            return candidateRepo.findByType(candidateType.toString()).stream().map(candidate ->
                    getCandidate(candidate.getId())
            ).collect(Collectors.toList());
        }
    }

    @Override
    public void updateCandidate(CandidateDto candidateDto, Long id) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        if(candidate != null){
            CandidateMapper.INSTANCE.updateFromDto(candidate, candidateDto);
            candidate.setId(id);
            candidateRepo.save(candidate);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public MessageResponse deleteCandidate(Long id) {
        MessageResponse messageResponse = new MessageResponse();
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        if(candidate != null){
            candidate.setStatus(Status.DELETED.toString());
            messageResponse.setMessage(String.join("Candidate id {d} is deleted.", id.toString()));
            candidateRepo.save(candidate);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return messageResponse;
    }


}
