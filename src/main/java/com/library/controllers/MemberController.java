package com.library.controllers;

import com.library.Dtos.MemberDto;
import com.library.Dtos.SignInDto;
import com.library.config.JwtTokenProvider;
import com.library.config.UserDetailsImplService;
import com.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/library/members")
@RestController
public class MemberController {
    @Autowired
    private final MemberService memberService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsImplService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<?> getMembers(){
        return this.memberService.getMembers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInDto requestDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(requestDto.getEmail());
        final String jwt = jwtTokenProvider.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable("memberId") Long id) {
        return this.memberService.getMember(id);
    }

    @PostMapping
    public ResponseEntity<?> addMember(@RequestBody MemberDto memberDto) {
        return this.memberService.addMember(memberDto);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> update(@PathVariable("memberId") Long id, @RequestBody MemberDto memberDto) {
        return this.memberService.update(id, memberDto);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> remove(@PathVariable("memberId") Long id) {
        return this.memberService.remove(id);
    }
}
