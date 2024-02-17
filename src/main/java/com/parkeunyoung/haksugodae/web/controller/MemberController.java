package com.parkeunyoung.haksugodae.web.controller;

import com.parkeunyoung.haksugodae.service.MemberService;
import com.parkeunyoung.haksugodae.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MemberDto.Request request, Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        memberService.register(request, auth.getName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/member")
    public ResponseEntity<MemberDto.Response> getMember(Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        MemberDto.Response member = memberService.getMember(auth.getName());
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }
}
