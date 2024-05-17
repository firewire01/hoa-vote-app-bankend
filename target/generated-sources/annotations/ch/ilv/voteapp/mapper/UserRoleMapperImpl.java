package ch.ilv.voteapp.mapper;

import ch.ilv.voteapp.dto.RoleDto;
import ch.ilv.voteapp.entity.UserRole;
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
public class UserRoleMapperImpl implements UserRoleMapper {

    @Override
    public RoleDto entityToDto(UserRole entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        return roleDto;
    }

    @Override
    public UserRole dtoToEnity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserRole userRole = new UserRole();

        return userRole;
    }

    @Override
    public List<UserRole> dtoToEnity(List<RoleDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<UserRole> list = new ArrayList<UserRole>( dto.size() );
        for ( RoleDto roleDto : dto ) {
            list.add( dtoToEnity( roleDto ) );
        }

        return list;
    }

    @Override
    public List<RoleDto> entityToDto(List<UserRole> dto) {
        if ( dto == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( dto.size() );
        for ( UserRole userRole : dto ) {
            list.add( entityToDto( userRole ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(UserRole entity, RoleDto dto) {
        if ( dto == null ) {
            return;
        }
    }

    @Override
    public void updateFromEntity(RoleDto dto, UserRole entity) {
        if ( entity == null ) {
            return;
        }
    }
}
