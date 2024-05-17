package ch.ilv.voteapp.mapper;

import ch.ilv.voteapp.dto.RoleDto;
import ch.ilv.voteapp.dto.VoterDto;
import ch.ilv.voteapp.entity.Role;
import ch.ilv.voteapp.entity.Status;
import ch.ilv.voteapp.entity.User;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-18T02:08:11+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
*/
@Component
public class VoterMapperImpl implements VoterMapper {

    @Override
    public VoterDto entityToDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        VoterDto voterDto = new VoterDto();

        voterDto.setId( entity.getId() );
        voterDto.setFname( entity.getFname() );
        voterDto.setLname( entity.getLname() );
        voterDto.setBlk( entity.getBlk() );
        voterDto.setLot( entity.getLot() );
        voterDto.setMigs( entity.isMigs() );
        voterDto.setEmail( entity.getEmail() );
        voterDto.setAge( entity.getAge() );
        voterDto.setDirectMember( entity.isDirectMember() );
        if ( entity.getStatus() != null ) {
            voterDto.setStatus( entity.getStatus().name() );
        }
        voterDto.setUsername( entity.getUsername() );
        voterDto.setTempPassword( entity.getTempPassword() );
        voterDto.setRoles( roleSetToRoleDtoList( entity.getRoles() ) );
        voterDto.setActivatedDate( entity.getActivatedDate() );
        voterDto.setVotedDate( entity.getVotedDate() );
        voterDto.setLastLogin( entity.getLastLogin() );

        return voterDto;
    }

    @Override
    public User dtoToEnity(VoterDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setFname( dto.getFname() );
        user.setLname( dto.getLname() );
        user.setAge( dto.getAge() );
        user.setBlk( dto.getBlk() );
        user.setLot( dto.getLot() );
        user.setMigs( dto.isMigs() );
        user.setEmail( dto.getEmail() );
        user.setDirectMember( dto.isDirectMember() );
        user.setTempPassword( dto.getTempPassword() );

        return user;
    }

    @Override
    public List<User> dtoToEnity(List<VoterDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dto.size() );
        for ( VoterDto voterDto : dto ) {
            list.add( dtoToEnity( voterDto ) );
        }

        return list;
    }

    @Override
    public List<VoterDto> entityToDto(List<User> dto) {
        if ( dto == null ) {
            return null;
        }

        List<VoterDto> list = new ArrayList<VoterDto>( dto.size() );
        for ( User user : dto ) {
            list.add( entityToDto( user ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(User entity, VoterDto dto) {
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
        entity.setDirectMember( dto.isDirectMember() );
        if ( dto.getStatus() != null ) {
            entity.setStatus( Enum.valueOf( Status.class, dto.getStatus() ) );
        }
        if ( dto.getTempPassword() != null ) {
            entity.setTempPassword( dto.getTempPassword() );
        }
        if ( dto.getActivatedDate() != null ) {
            entity.setActivatedDate( new Date( dto.getActivatedDate().getTime() ) );
        }
        if ( dto.getVotedDate() != null ) {
            entity.setVotedDate( new Date( dto.getVotedDate().getTime() ) );
        }
        if ( dto.getLastLogin() != null ) {
            entity.setLastLogin( new Date( dto.getLastLogin().getTime() ) );
        }
        if ( dto.getUsername() != null ) {
            entity.setUsername( dto.getUsername() );
        }
        if ( entity.getRoles() != null ) {
            Set<Role> set = roleDtoListToRoleSet( dto.getRoles() );
            if ( set != null ) {
                entity.getRoles().clear();
                entity.getRoles().addAll( set );
            }
        }
        else {
            Set<Role> set = roleDtoListToRoleSet( dto.getRoles() );
            if ( set != null ) {
                entity.setRoles( set );
            }
        }
    }

    @Override
    public void updateFromEntity(VoterDto dto, User entity) {
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
        dto.setDirectMember( entity.isDirectMember() );
        if ( entity.getStatus() != null ) {
            dto.setStatus( entity.getStatus().name() );
        }
        if ( entity.getUsername() != null ) {
            dto.setUsername( entity.getUsername() );
        }
        if ( entity.getTempPassword() != null ) {
            dto.setTempPassword( entity.getTempPassword() );
        }
        if ( dto.getRoles() != null ) {
            List<RoleDto> list = roleSetToRoleDtoList( entity.getRoles() );
            if ( list != null ) {
                dto.getRoles().clear();
                dto.getRoles().addAll( list );
            }
        }
        else {
            List<RoleDto> list = roleSetToRoleDtoList( entity.getRoles() );
            if ( list != null ) {
                dto.setRoles( list );
            }
        }
        if ( entity.getActivatedDate() != null ) {
            dto.setActivatedDate( entity.getActivatedDate() );
        }
        if ( entity.getVotedDate() != null ) {
            dto.setVotedDate( entity.getVotedDate() );
        }
        if ( entity.getLastLogin() != null ) {
            dto.setLastLogin( entity.getLastLogin() );
        }
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setName( role.getName() );

        return roleDto;
    }

    protected List<RoleDto> roleSetToRoleDtoList(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( set.size() );
        for ( Role role : set ) {
            list.add( roleToRoleDto( role ) );
        }

        return list;
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setName( roleDto.getName() );

        return role;
    }

    protected Set<Role> roleDtoListToRoleSet(List<RoleDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<Role> set = new LinkedHashSet<Role>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( RoleDto roleDto : list ) {
            set.add( roleDtoToRole( roleDto ) );
        }

        return set;
    }
}
