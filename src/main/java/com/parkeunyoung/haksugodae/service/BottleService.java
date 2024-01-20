package com.parkeunyoung.haksugodae.service;


import com.parkeunyoung.haksugodae.domain.bottle.Bottle;
import com.parkeunyoung.haksugodae.domain.bottle.BottleRepository;
import com.parkeunyoung.haksugodae.domain.member.Member;
import com.parkeunyoung.haksugodae.domain.member.MemberRepository;
import com.parkeunyoung.haksugodae.web.dto.BottleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BottleService {

    private final BottleRepository bottleRepository;
    private final MemberRepository memberRepository;

    public String makeBottle(BottleDto.Detail detail, String name) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        bottleRepository.save(Bottle.builder()
                .title(detail.getTitle())
                .dDay(detail.getDDay())
                .bottleColor(detail.getBottleColor())
                .bottleDesign(detail.getBottleDesign())
                .showOrNot(detail.getShowOrNot())
                .member(member)
                .build());

        return "생성";
    }

    public String deleteBottle(BottleDto.Id id, String name) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        List<Bottle> bottles = bottleRepository.findByMember(member);
        for (Bottle bottle : bottles) {
            if (bottle.getBottleId() == id.getBottleId()) {
                bottleRepository.delete(bottle);
                return "삭제";
            }
        }
        return "본인의 것이 아닙니다";
    }

    public BottleDto.SummaryList showBottleMyMember(String name) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        List<Bottle> bottles = bottleRepository.findByMember(member);

        BottleDto.SummaryList.SummaryListBuilder listBuilder = BottleDto.SummaryList.builder();

        for (Bottle bottle : bottles) {
            BottleDto.Summary summary = BottleDto.Summary.builder()
                    .bottleId(bottle.getBottleId())
                    .title(bottle.getTitle())
                    .bottleColor(bottle.getBottleColor())
                    .bottleDesign(bottle.getBottleDesign())
                    .build();
            listBuilder.bottles(Collections.singletonList(summary));
        }
        return listBuilder.build();
    }
}
