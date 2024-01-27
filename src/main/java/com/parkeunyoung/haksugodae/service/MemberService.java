package com.parkeunyoung.haksugodae.service;

import com.parkeunyoung.haksugodae.domain.member.Member;
import com.parkeunyoung.haksugodae.domain.member.MemberRepository;
import com.parkeunyoung.haksugodae.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String register(MemberDto.Request request, String name) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다."));
        member.updateNickname(request.getNickname());
        return "변경";
    }

    public Member getMemberByName(String name) {
        return memberRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
    }

    public MemberDto.Response getMember(String name) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        return MemberDto.Response.builder()
                .isLogin(!member.getCreatedDate().equals(member.getModifiedDate()))
                .nickname(member.getNickname())
                .build();
    }
}
