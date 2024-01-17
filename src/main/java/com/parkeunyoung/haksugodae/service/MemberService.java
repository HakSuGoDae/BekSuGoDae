package com.parkeunyoung.haksugodae.service;

import com.parkeunyoung.haksugodae.domain.member.Member;
import com.parkeunyoung.haksugodae.domain.member.MemberRepository;
import com.parkeunyoung.haksugodae.web.dto.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String register(MemberRequestDto memberRequestDto, String name) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));
        member.updateNickname(memberRequestDto.getNickname());
        return "변경";
    }

    public Member getMemberByName(String name) {
        return memberRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
    }
}
