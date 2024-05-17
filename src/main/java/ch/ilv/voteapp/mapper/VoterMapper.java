package ch.ilv.voteapp.mapper;

import ch.ilv.voteapp.dto.VoterDto;
import ch.ilv.voteapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface VoterMapper{
    VoterMapper INSTANCE = Mappers.getMapper( VoterMapper.class );


    VoterDto entityToDto(User entity);

    @Mapping(target = "activatedDate", ignore = true)
    @Mapping(target = "votedDate", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    User dtoToEnity(VoterDto dto);

    List<User> dtoToEnity(List<VoterDto> dto);

    List<VoterDto> entityToDto(List<User> dto);

    void updateFromDto(@MappingTarget User entity, VoterDto dto);

    void updateFromEntity(@MappingTarget VoterDto dto, User entity);
}
