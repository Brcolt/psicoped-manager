package br.com.coltextends.psicopediatria.mappers;


import br.com.coltextends.psicopediatria.dto.TutorDTO;
import br.com.coltextends.psicopediatria.model.Tutor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TutorMapper {

    TutorMapper INSTANCE = Mappers.getMapper(TutorMapper.class);

    public Tutor toModel(TutorDTO tutorDTO);

    public TutorDTO toDTO(Tutor tutor);

}
