package ch.ilv.voteapp.controller;

import ch.ilv.voteapp.base.MessageResponse;
import ch.ilv.voteapp.dto.AuthenticationRequest;
import ch.ilv.voteapp.dto.AuthenticationResponse;
import ch.ilv.voteapp.dto.JwtResponse;
import ch.ilv.voteapp.dto.UpdatePassDto;
import ch.ilv.voteapp.security.JwtUtils;
import ch.ilv.voteapp.security.UserDetailsImpl;
import ch.ilv.voteapp.service.VoterService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class LogInController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private VoterService voterService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @Valid @RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
//jwt, userDetails.getId(), , userDetails.getUsername(), userDetails.getEmail(), roles

        return ResponseEntity
                .ok(JwtResponse.builder().id(userDetails.getId()).token(jwt).email(userDetails.getEmail()).roles(roles)
                        .username(userDetails.getUsername()).build());
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/check")
    public ResponseEntity<MessageResponse> checkUser(@PathParam("username") String username) {
        try {
            return ResponseEntity.ok(voterService.checkUser(username));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/activate")
    public ResponseEntity<MessageResponse> updatePassword(@Valid @RequestBody UpdatePassDto updatePassDto) {
        try {
            return ResponseEntity.ok(voterService.updatePass(updatePassDto, encoder));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }




}
