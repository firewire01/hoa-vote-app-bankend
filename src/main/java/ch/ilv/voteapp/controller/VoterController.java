package ch.ilv.voteapp.controller;

import ch.ilv.voteapp.base.MessageResponse;
import ch.ilv.voteapp.dto.*;
import ch.ilv.voteapp.entity.CandidateType;
import ch.ilv.voteapp.security.Roles;
import ch.ilv.voteapp.service.VoterService;
import ch.ilv.voteapp.service.interf.VoterServiceInt;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@SecurityRequirement(name="bearerAuth")
@RestController
@Validated
public class VoterController {

    public final VoterServiceInt voterService;

    @Autowired
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping("api/v1/vote/{type}")
    @RolesAllowed({Roles.ADMIN})
    public ResponseEntity<List<UserVoteDto>> getAll(@PathVariable CandidateType type) {
        List<UserVoteDto> result = voterService.getCandidateByType(type);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("api/v1/vote")
    @RolesAllowed(Roles.VOTER)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<MessageResponse> newVote(@RequestBody VoteDto voterDto) {
        try {
            return ResponseEntity.ok(voterService.vote(voterDto));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("api/v1/voter/{id}")
    @RolesAllowed({Roles.VOTER, Roles.ADMIN})
    public ResponseEntity<VoterDto> one(@PathVariable Long id) {
        VoterDto voterDto = voterService.getVoter(id);
        return new ResponseEntity<>(voterDto, HttpStatus.OK);
    }

    @GetMapping("api/v1/voter")
    @RolesAllowed({Roles.VOTER, Roles.ADMIN})
    public ResponseEntity<List<VoterDto>> getAll() {
        return new ResponseEntity<>(voterService.getAll(), HttpStatus.OK);
    }
    //
    @PostMapping("api/v1/voter")
    @RolesAllowed(Roles.ADMIN)
    @ResponseStatus(value = HttpStatus.OK)
    public void addVoter(@Valid @RequestBody VoterDto voterDto) {
        voterService.addVoter(voterDto);
    }

    @PostMapping(path = "api/v1/voter/bulk")
//    @RolesAllowed(Roles.ADMIN)
//    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<MessageResponse> bulkAddVoter(@RequestParam(value = "file", required = true) MultipartFile file) {
        try {
            return ResponseEntity.ok(voterService.bulkAddVoter(file));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
    //
    @PutMapping("api/v1/voter/{id}")
//    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<VoterDto> updatePerson(@Valid @RequestBody VoterDto voterDto, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(voterService.updateVoter(voterDto, id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
    //
    @DeleteMapping("api/v1/voter/{id}")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<MessageResponse> deletePerson(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(voterService.deleteVoter(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("api/v1/voter/reset/{id}")
    @RolesAllowed(Roles.ADMIN)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<MessageResponse> resetVotersPass(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(voterService.resetUser(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("api/v1/voter/pass")
//    @RolesAllowed(Roles.ADMIN)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<MessageResponse> updatePass(@RequestBody UpdatePassDto updatePassDto) {
        try {
            return ResponseEntity.ok(voterService.updatePass(updatePassDto, new BCryptPasswordEncoder()));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
