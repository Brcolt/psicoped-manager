package br.com.coltextends.psicopediatria.controller.tutor;


import br.com.coltextends.psicopediatria.dto.TutorDTO;
import br.com.coltextends.psicopediatria.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("api/v1/tutors")
public class TutorController {

    private final TutorService tutorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TutorDTO create(@RequestBody TutorDTO tutorDTO) {
        return tutorService.create(tutorDTO);
    }
}
