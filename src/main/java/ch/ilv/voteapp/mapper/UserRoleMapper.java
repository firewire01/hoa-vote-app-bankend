package ch.ilv.voteapp.mapper;

import ch.ilv.voteapp.dto.RoleDto;
import ch.ilv.voteapp.dto.VoterDto;
import ch.ilv.voteapp.entity.User;
import ch.ilv.voteapp.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface UserRoleMapper extends MapperBase<UserRole, RoleDto>{
    UserRoleMapper INSTANCE = Mappers.getMapper( UserRoleMapper.class );
}
