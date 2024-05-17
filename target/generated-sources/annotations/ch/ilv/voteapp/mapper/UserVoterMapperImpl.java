package ch.ilv.voteapp.mapper;

import ch.ilv.voteapp.dto.UserVoteDto;
import ch.ilv.voteapp.entity.UserVote;
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
public class UserVoterMapperImpl implements UserVoterMapper {

    @Override
    public UserVoteDto entityToDto(UserVote entity) {
        if ( entity == null ) {
            return null;
        }

        UserVoteDto userVoteDto = new UserVoteDto();

        if ( entity.getCreatedBy() != null ) {
            userVoteDto.setCreatedBy( entity.getCreatedBy().intValue() );
        }
        userVoteDto.setCreatedDate( entity.getCreatedDate() );
        if ( entity.getUpdatedBy() != null ) {
            userVoteDto.setUpdatedBy( entity.getUpdatedBy().intValue() );
        }
        userVoteDto.setUpdatedDate( entity.getUpdatedDate() );

        return userVoteDto;
    }

    @Override
    public UserVote dtoToEnity(UserVoteDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserVote userVote = new UserVote();

        userVote.setCreatedBy( (long) dto.getCreatedBy() );
        userVote.setCreatedDate( dto.getCreatedDate() );
        userVote.setUpdatedBy( (long) dto.getUpdatedBy() );
        userVote.setUpdatedDate( dto.getUpdatedDate() );

        return userVote;
    }

    @Override
    public List<UserVote> dtoToEnity(List<UserVoteDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<UserVote> list = new ArrayList<UserVote>( dto.size() );
        for ( UserVoteDto userVoteDto : dto ) {
            list.add( dtoToEnity( userVoteDto ) );
        }

        return list;
    }

    @Override
    public List<UserVoteDto> entityToDto(List<UserVote> dto) {
        if ( dto == null ) {
            return null;
        }

        List<UserVoteDto> list = new ArrayList<UserVoteDto>( dto.size() );
        for ( UserVote userVote : dto ) {
            list.add( entityToDto( userVote ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(UserVote entity, UserVoteDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setCreatedBy( (long) dto.getCreatedBy() );
        if ( dto.getCreatedDate() != null ) {
            entity.setCreatedDate( dto.getCreatedDate() );
        }
        entity.setUpdatedBy( (long) dto.getUpdatedBy() );
        if ( dto.getUpdatedDate() != null ) {
            entity.setUpdatedDate( dto.getUpdatedDate() );
        }
    }

    @Override
    public void updateFromEntity(UserVoteDto dto, UserVote entity) {
        if ( entity == null ) {
            return;
        }

        if ( entity.getCreatedBy() != null ) {
            dto.setCreatedBy( entity.getCreatedBy().intValue() );
        }
        if ( entity.getCreatedDate() != null ) {
            dto.setCreatedDate( entity.getCreatedDate() );
        }
        if ( entity.getUpdatedBy() != null ) {
            dto.setUpdatedBy( entity.getUpdatedBy().intValue() );
        }
        if ( entity.getUpdatedDate() != null ) {
            dto.setUpdatedDate( entity.getUpdatedDate() );
        }
    }
}
