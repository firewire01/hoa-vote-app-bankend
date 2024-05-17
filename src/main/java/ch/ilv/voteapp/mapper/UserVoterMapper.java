package ch.ilv.voteapp.mapper;

import ch.ilv.voteapp.dto.UserVoteDto;
import ch.ilv.voteapp.entity.UserVote;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface UserVoterMapper extends MapperBase<UserVote, UserVoteDto>{
  UserVoterMapper INSTANCE = Mappers.getMapper( UserVoterMapper.class );
}
