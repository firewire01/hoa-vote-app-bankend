package ch.ilv.voteapp.mapper;

import ch.ilv.voteapp.dto.CandidateDto;
import ch.ilv.voteapp.entity.Candidate;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface MapperBase<E, D> {

    D entityToDto(E entity);

    E dtoToEnity(D dto);

    List<E> dtoToEnity(List<D> dto);

    List<D> entityToDto(List<E> dto);

    void updateFromDto(@MappingTarget E entity, D dto);

    void updateFromEntity(@MappingTarget D dto, E entity);
}
