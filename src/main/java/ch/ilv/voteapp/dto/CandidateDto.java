package ch.ilv.voteapp.dto;

import ch.ilv.voteapp.entity.CandidateType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateDto extends PersonDto{
    private boolean violation = false;
    private boolean incumbent = false;
    private int siblings = 0;
    private int yearsInMarried = 0;
    private int yearsInHoa = 0;
    private String facebook;
    private String linkedin;
    private String industry;
    private CandidateType candidateType;
    private String mname;
    private String currentJob;
    private String lastPosition;

    private FileDTO fileDTO;

    private long canidateVote;
    private double percentile;

    private String contactNumber;
    private List<GoalsDto> goals;
    private List<RelevantActivitiesDto> relevantActivities;
    private List<IntroStatementsDto> introStatements;
}
