package com.parkeunyoung.haksugodae.web.controller;

import com.parkeunyoung.haksugodae.service.MemberService;
import com.parkeunyoung.haksugodae.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
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
    public String register(@RequestBody MemberDto.Request request, Authentication auth) {
        return memberService.register(request, auth.getName());
    }

    @GetMapping("/member")
    public MemberDto.Response getMember(Authentication auth) {
        return memberService.getMember(auth.getName());
    }
}
