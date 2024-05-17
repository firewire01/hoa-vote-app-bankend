package ch.ilv.voteapp.controller;

import ch.ilv.voteapp.dto.HeaderResponse;
import ch.ilv.voteapp.security.Roles;
import ch.ilv.voteapp.service.interf.HeaderServiceInt;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeaderController {

    private final HeaderServiceInt headerService;

    public HeaderController(HeaderServiceInt headerService) {
        this.headerService = headerService;
    }

    @GetMapping("api/v1/header")
    @RolesAllowed(Roles.ADMIN)
    public ResponseEntity<HeaderResponse> header() {
        return new ResponseEntity<>(headerService.getHeaders(), HttpStatus.OK);
    }
}
