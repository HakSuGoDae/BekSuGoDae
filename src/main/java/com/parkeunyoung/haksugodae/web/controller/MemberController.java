package com.parkeunyoung.haksugodae.web.controller;

import com.parkeunyoung.haksugodae.service.MemberService;
import com.parkeunyoung.haksugodae.web.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public String register(MemberRequestDto memberRequestDto, Authentication auth) {
        return memberService.register(memberRequestDto, auth.getName());
    }
}
