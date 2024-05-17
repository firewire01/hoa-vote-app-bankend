package ch.ilv.voteapp.mapper;

import ch.ilv.voteapp.dto.CandidateDto;
import ch.ilv.voteapp.dto.GoalsDto;
import ch.ilv.voteapp.dto.IntroStatementsDto;
import ch.ilv.voteapp.dto.RelevantActivitiesDto;
import ch.ilv.voteapp.entity.Candidate;
import ch.ilv.voteapp.entity.Goals;
import ch.ilv.voteapp.entity.IntroStatements;
import ch.ilv.voteapp.entity.RelevantActivities;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-18T02:08:11+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
*/
@Component
public class CandidateMapperImpl implements CandidateMapper {

    @Override
    public CandidateDto entityToDto(Candidate entity) {
        if ( entity == null ) {
            return null;
        }

        CandidateDto candidateDto = new CandidateDto();

        candidateDto.setId( entity.getId() );
        candidateDto.setFname( entity.getFname() );
        candidateDto.setLname( entity.getLname() );
        candidateDto.setBlk( entity.getBlk() );
        candidateDto.setLot( entity.getLot() );
        candidateDto.setMigs( entity.isMigs() );
        candidateDto.setEmail( entity.getEmail() );
        candidateDto.setAge( entity.getAge() );
        candidateDto.setViolation( entity.isViolation() );
        candidateDto.setIncumbent( entity.isIncumbent() );
        candidateDto.setSiblings( entity.getSiblings() );
        candidateDto.setYearsInMarried( entity.getYearsInMarried() );
        candidateDto.setYearsInHoa( entity.getYearsInHoa() );
        candidateDto.setFacebook( entity.getFacebook() );
        candidateDto.setLinkedin( entity.getLinkedin() );
        candidateDto.setIndustry( entity.getIndustry() );
        candidateDto.setMname( entity.getMname() );
        candidateDto.setCurrentJob( entity.getCurrentJob() );
        candidateDto.setLastPosition( entity.getLastPosition() );
        candidateDto.setContactNumber( entity.getContactNumber() );
        candidateDto.setGoals( goalsListToGoalsDtoList( entity.getGoals() ) );
        candidateDto.setRelevantActivities( relevantActivitiesListToRelevantActivitiesDtoList( entity.getRelevantActivities() ) );
        candidateDto.setIntroStatements( introStatementsListToIntroStatementsDtoList( entity.getIntroStatements() ) );

        return candidateDto;
    }

    @Override
    public Candidate dtoToEnity(CandidateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Candidate candidate = new Candidate();

        candidate.setId( dto.getId() );
        candidate.setFname( dto.getFname() );
        candidate.setLname( dto.getLname() );
        candidate.setAge( dto.getAge() );
        candidate.setBlk( dto.getBlk() );
        candidate.setLot( dto.getLot() );
        candidate.setMigs( dto.isMigs() );
        candidate.setEmail( dto.getEmail() );
        candidate.setViolation( dto.isViolation() );
        candidate.setIncumbent( dto.isIncumbent() );
        candidate.setSiblings( dto.getSiblings() );
        candidate.setYearsInMarried( dto.getYearsInMarried() );
        candidate.setYearsInHoa( dto.getYearsInHoa() );
        candidate.setFacebook( dto.getFacebook() );
        candidate.setLinkedin( dto.getLinkedin() );
        candidate.setIndustry( dto.getIndustry() );
        candidate.setMname( dto.getMname() );
        candidate.setContactNumber( dto.getContactNumber() );
        candidate.setCurrentJob( dto.getCurrentJob() );
        candidate.setLastPosition( dto.getLastPosition() );
        candidate.setIntroStatements( introStatementsDtoListToIntroStatementsList( dto.getIntroStatements() ) );
        candidate.setGoals( goalsDtoListToGoalsList( dto.getGoals() ) );
        candidate.setRelevantActivities( relevantActivitiesDtoListToRelevantActivitiesList( dto.getRelevantActivities() ) );

        return candidate;
    }

    @Override
    public List<Candidate> dtoToEnity(List<CandidateDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Candidate> list = new ArrayList<Candidate>( dto.size() );
        for ( CandidateDto candidateDto : dto ) {
            list.add( dtoToEnity( candidateDto ) );
        }

        return list;
    }

    @Override
    public List<CandidateDto> entityToDto(List<Candidate> dto) {
        if ( dto == null ) {
            return null;
        }

        List<CandidateDto> list = new ArrayList<CandidateDto>( dto.size() );
        for ( Candidate candidate : dto ) {
            list.add( entityToDto( candidate ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(Candidate entity, CandidateDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getFname() != null ) {
            entity.setFname( dto.getFname() );
        }
        if ( dto.getLname() != null ) {
            entity.setLname( dto.getLname() );
        }
        entity.setAge( dto.getAge() );
        entity.setBlk( dto.getBlk() );
        entity.setLot( dto.getLot() );
        entity.setMigs( dto.isMigs() );
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        entity.setViolation( dto.isViolation() );
        entity.setIncumbent( dto.isIncumbent() );
        entity.setSiblings( dto.getSiblings() );
        entity.setYearsInMarried( dto.getYearsInMarried() );
        entity.setYearsInHoa( dto.getYearsInHoa() );
        if ( dto.getFacebook() != null ) {
            entity.setFacebook( dto.getFacebook() );
        }
        if ( dto.getLinkedin() != null ) {
            entity.setLinkedin( dto.getLinkedin() );
        }
        if ( dto.getIndustry() != null ) {
            entity.setIndustry( dto.getIndustry() );
        }
        if ( dto.getMname() != null ) {
            entity.setMname( dto.getMname() );
        }
        if ( dto.getContactNumber() != null ) {
            entity.setContactNumber( dto.getContactNumber() );
        }
        if ( dto.getCurrentJob() != null ) {
            entity.setCurrentJob( dto.getCurrentJob() );
        }
        if ( dto.getLastPosition() != null ) {
            entity.setLastPosition( dto.getLastPosition() );
        }
        if ( entity.getIntroStatements() != null ) {
            List<IntroStatements> list = introStatementsDtoListToIntroStatementsList( dto.getIntroStatements() );
            if ( list != null ) {
                entity.getIntroStatements().clear();
                entity.getIntroStatements().addAll( list );
            }
        }
        else {
            List<IntroStatements> list = introStatementsDtoListToIntroStatementsList( dto.getIntroStatements() );
            if ( list != null ) {
                entity.setIntroStatements( list );
            }
        }
        if ( entity.getGoals() != null ) {
            List<Goals> list1 = goalsDtoListToGoalsList( dto.getGoals() );
            if ( list1 != null ) {
                entity.getGoals().clear();
                entity.getGoals().addAll( list1 );
            }
        }
        else {
            List<Goals> list1 = goalsDtoListToGoalsList( dto.getGoals() );
            if ( list1 != null ) {
                entity.setGoals( list1 );
            }
        }
        if ( entity.getRelevantActivities() != null ) {
            List<RelevantActivities> list2 = relevantActivitiesDtoListToRelevantActivitiesList( dto.getRelevantActivities() );
            if ( list2 != null ) {
                entity.getRelevantActivities().clear();
                entity.getRelevantActivities().addAll( list2 );
            }
        }
        else {
            List<RelevantActivities> list2 = relevantActivitiesDtoListToRelevantActivitiesList( dto.getRelevantActivities() );
            if ( list2 != null ) {
                entity.setRelevantActivities( list2 );
            }
        }
    }

    @Override
    public void updateFromEntity(CandidateDto dto, Candidate entity) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getId() != null ) {
            dto.setId( entity.getId() );
        }
        if ( entity.getFname() != null ) {
            dto.setFname( entity.getFname() );
        }
        if ( entity.getLname() != null ) {
            dto.setLname( entity.getLname() );
        }
        dto.setBlk( entity.getBlk() );
        dto.setLot( entity.getLot() );
        dto.setMigs( entity.isMigs() );
        if ( entity.getEmail() != null ) {
            dto.setEmail( entity.getEmail() );
        }
        dto.setAge( entity.getAge() );
        dto.setViolation( entity.isViolation() );
        dto.setIncumbent( entity.isIncumbent() );
        dto.setSiblings( entity.getSiblings() );
        dto.setYearsInMarried( entity.getYearsInMarried() );
        dto.setYearsInHoa( entity.getYearsInHoa() );
        if ( entity.getFacebook() != null ) {
            dto.setFacebook( entity.getFacebook() );
        }
        if ( entity.getLinkedin() != null ) {
            dto.setLinkedin( entity.getLinkedin() );
        }
        if ( entity.getIndustry() != null ) {
            dto.setIndustry( entity.getIndustry() );
        }
        if ( entity.getMname() != null ) {
            dto.setMname( entity.getMname() );
        }
        if ( entity.getCurrentJob() != null ) {
            dto.setCurrentJob( entity.getCurrentJob() );
        }
        if ( entity.getLastPosition() != null ) {
            dto.setLastPosition( entity.getLastPosition() );
        }
        if ( entity.getContactNumber() != null ) {
            dto.setContactNumber( entity.getContactNumber() );
        }
        if ( dto.getGoals() != null ) {
            List<GoalsDto> list = goalsListToGoalsDtoList( entity.getGoals() );
            if ( list != null ) {
                dto.getGoals().clear();
                dto.getGoals().addAll( list );
            }
        }
        else {
            List<GoalsDto> list = goalsListToGoalsDtoList( entity.getGoals() );
            if ( list != null ) {
                dto.setGoals( list );
            }
        }
        if ( dto.getRelevantActivities() != null ) {
            List<RelevantActivitiesDto> list1 = relevantActivitiesListToRelevantActivitiesDtoList( entity.getRelevantActivities() );
            if ( list1 != null ) {
                dto.getRelevantActivities().clear();
                dto.getRelevantActivities().addAll( list1 );
            }
        }
        else {
            List<RelevantActivitiesDto> list1 = relevantActivitiesListToRelevantActivitiesDtoList( entity.getRelevantActivities() );
            if ( list1 != null ) {
                dto.setRelevantActivities( list1 );
            }
        }
        if ( dto.getIntroStatements() != null ) {
            List<IntroStatementsDto> list2 = introStatementsListToIntroStatementsDtoList( entity.getIntroStatements() );
            if ( list2 != null ) {
                dto.getIntroStatements().clear();
                dto.getIntroStatements().addAll( list2 );
            }
        }
        else {
            List<IntroStatementsDto> list2 = introStatementsListToIntroStatementsDtoList( entity.getIntroStatements() );
            if ( list2 != null ) {
                dto.setIntroStatements( list2 );
            }
        }
    }

    protected GoalsDto goalsToGoalsDto(Goals goals) {
        if ( goals == null ) {
            return null;
        }

        GoalsDto goalsDto = new GoalsDto();

        goalsDto.setId( goals.getId() );
        goalsDto.setDescription( goals.getDescription() );

        return goalsDto;
    }

    protected List<GoalsDto> goalsListToGoalsDtoList(List<Goals> list) {
        if ( list == null ) {
            return null;
        }

        List<GoalsDto> list1 = new ArrayList<GoalsDto>( list.size() );
        for ( Goals goals : list ) {
            list1.add( goalsToGoalsDto( goals ) );
        }

        return list1;
    }

    protected RelevantActivitiesDto relevantActivitiesToRelevantActivitiesDto(RelevantActivities relevantActivities) {
        if ( relevantActivities == null ) {
            return null;
        }

        RelevantActivitiesDto relevantActivitiesDto = new RelevantActivitiesDto();

        relevantActivitiesDto.setId( relevantActivities.getId() );
        relevantActivitiesDto.setDescription( relevantActivities.getDescription() );

        return relevantActivitiesDto;
    }

    protected List<RelevantActivitiesDto> relevantActivitiesListToRelevantActivitiesDtoList(List<RelevantActivities> list) {
        if ( list == null ) {
            return null;
        }

        List<RelevantActivitiesDto> list1 = new ArrayList<RelevantActivitiesDto>( list.size() );
        for ( RelevantActivities relevantActivities : list ) {
            list1.add( relevantActivitiesToRelevantActivitiesDto( relevantActivities ) );
        }

        return list1;
    }

    protected IntroStatementsDto introStatementsToIntroStatementsDto(IntroStatements introStatements) {
        if ( introStatements == null ) {
            return null;
        }

        IntroStatementsDto introStatementsDto = new IntroStatementsDto();

        introStatementsDto.setId( introStatements.getId() );
        introStatementsDto.setDescription( introStatements.getDescription() );

        return introStatementsDto;
    }

    protected List<IntroStatementsDto> introStatementsListToIntroStatementsDtoList(List<IntroStatements> list) {
        if ( list == null ) {
            return null;
        }

        List<IntroStatementsDto> list1 = new ArrayList<IntroStatementsDto>( list.size() );
        for ( IntroStatements introStatements : list ) {
            list1.add( introStatementsToIntroStatementsDto( introStatements ) );
        }

        return list1;
    }

    protected IntroStatements introStatementsDtoToIntroStatements(IntroStatementsDto introStatementsDto) {
        if ( introStatementsDto == null ) {
            return null;
        }

        IntroStatements introStatements = new IntroStatements();

        introStatements.setId( introStatementsDto.getId() );
        introStatements.setDescription( introStatementsDto.getDescription() );

        return introStatements;
    }

    protected List<IntroStatements> introStatementsDtoListToIntroStatementsList(List<IntroStatementsDto> list) {
        if ( list == null ) {
            return null;
        }

        List<IntroStatements> list1 = new ArrayList<IntroStatements>( list.size() );
        for ( IntroStatementsDto introStatementsDto : list ) {
            list1.add( introStatementsDtoToIntroStatements( introStatementsDto ) );
        }

        return list1;
    }

    protected Goals goalsDtoToGoals(GoalsDto goalsDto) {
        if ( goalsDto == null ) {
            return null;
        }

        Goals goals = new Goals();

        goals.setId( goalsDto.getId() );
        goals.setDescription( goalsDto.getDescription() );

        return goals;
    }

    protected List<Goals> goalsDtoListToGoalsList(List<GoalsDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Goals> list1 = new ArrayList<Goals>( list.size() );
        for ( GoalsDto goalsDto : list ) {
            list1.add( goalsDtoToGoals( goalsDto ) );
        }

        return list1;
    }

    protected RelevantActivities relevantActivitiesDtoToRelevantActivities(RelevantActivitiesDto relevantActivitiesDto) {
        if ( relevantActivitiesDto == null ) {
            return null;
        }

        RelevantActivities relevantActivities = new RelevantActivities();

        relevantActivities.setId( relevantActivitiesDto.getId() );
        relevantActivities.setDescription( relevantActivitiesDto.getDescription() );

        return relevantActivities;
    }

    protected List<RelevantActivities> relevantActivitiesDtoListToRelevantActivitiesList(List<RelevantActivitiesDto> list) {
        if ( list == null ) {
            return null;
        }

        List<RelevantActivities> list1 = new ArrayList<RelevantActivities>( list.size() );
        for ( RelevantActivitiesDto relevantActivitiesDto : list ) {
            list1.add( relevantActivitiesDtoToRelevantActivities( relevantActivitiesDto ) );
        }

        return list1;
    }
}
