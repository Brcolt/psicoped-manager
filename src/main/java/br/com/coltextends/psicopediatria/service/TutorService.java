package br.com.coltextends.psicopediatria.service;

import br.com.coltextends.psicopediatria.dto.TutorDTO;
import br.com.coltextends.psicopediatria.mappers.TutorMapper;
import br.com.coltextends.psicopediatria.model.Tutor;
import br.com.coltextends.psicopediatria.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TutorService {

    private final TutorRepository tutorRepository;
    private final TutorMapper tutorMapper = TutorMapper.INSTANCE;
    private List<String> ignoredProps = Arrays.asList("id");

    public TutorDTO create(TutorDTO tutorDTO) {
        Tutor tutor = tutorRepository.save(tutorMapper.toModel(tutorDTO));
        tutor.setAddresses(tutorDTO.getAddresses());
        return tutorMapper.toDTO(tutor);
    }
}
