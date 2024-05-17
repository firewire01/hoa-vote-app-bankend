package ch.ilv.voteapp.controller;

import ch.ilv.voteapp.base.MessageResponse;
import ch.ilv.voteapp.dto.CandidateDto;
import ch.ilv.voteapp.dto.FileDTO;
import ch.ilv.voteapp.dto.ImagePicture;
import ch.ilv.voteapp.entity.Candidate;
import ch.ilv.voteapp.entity.CandidateType;
import ch.ilv.voteapp.security.Roles;
import ch.ilv.voteapp.service.CandidateService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@SecurityRequirement(name="bearerAuth")
@RestController
@Validated
public class CandidateController {

    public final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("api/v1/candidate")
    public ResponseEntity<List<CandidateDto>> all(@RequestParam("type") CandidateType candidateType) {
        List<CandidateDto> result = candidateService.getCandidates(candidateType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/v1/candidate/voter/{id}")
    public ResponseEntity<List<CandidateDto>> getAllVoter(@PathVariable Long id) {
        List<CandidateDto> result = candidateService.getAllVoter(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
//
    @GetMapping("api/v1/candidate/{id}")
    public ResponseEntity<CandidateDto> one(@PathVariable Long id) {
        CandidateDto candidate = candidateService.getCandidate(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }
//
    @PostMapping("api/v1/candidate")
//    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<CandidateDto> addCandidate(@Valid @RequestBody CandidateDto candidateDto) {
        try {
            return ResponseEntity.ok(candidateService.addCandidate(candidateDto));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("api/v1/candidate/image/{id}")
//    @RolesAllowed(Roles.ADMIN)
    @ResponseStatus(value = HttpStatus.OK)
    public void addProfile(@PathVariable Long id, @Valid @RequestBody FileDTO fileDTO) {
        candidateService.addProfilepic(id, fileDTO);
    }

    @GetMapping("api/v1/candidate/image/{id}")
//    @RolesAllowed(Roles.ADMIN)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ImagePicture> getProfilePic(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(candidateService.getProfilePic(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }

    }
//
    @PutMapping("api/v1/candidate/{id}")
//    @RolesAllowed(Roles.ADMIN)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updatePerson(@Valid @RequestBody CandidateDto candidateDto, @PathVariable Long id) {
        candidateService.updateCandidate(candidateDto, id);
    }
//
    @DeleteMapping("api/v1/candidate/{id}")
//    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<MessageResponse> deletePerson(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(candidateService.deleteCandidate(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
