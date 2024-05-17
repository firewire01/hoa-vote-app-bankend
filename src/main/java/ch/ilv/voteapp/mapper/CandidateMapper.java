package ch.ilv.voteapp.mapper;

import ch.ilv.voteapp.dto.CandidateDto;
import ch.ilv.voteapp.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface CandidateMapper extends MapperBase<Candidate, CandidateDto>{

  CandidateMapper INSTANCE = Mappers.getMapper( CandidateMapper.class );
}
