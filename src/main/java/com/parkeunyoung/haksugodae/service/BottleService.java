package com.parkeunyoung.haksugodae.service;


import com.parkeunyoung.haksugodae.domain.bottle.Bottle;
import com.parkeunyoung.haksugodae.domain.bottle.BottleRepository;
import com.parkeunyoung.haksugodae.domain.member.Member;
import com.parkeunyoung.haksugodae.domain.member.MemberRepository;
import com.parkeunyoung.haksugodae.web.dto.BottleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                .paperCraneCnt(0L)
                .view(false)
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

    public List<BottleDto.Summary> showBottleByMember(String name) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        List<Bottle> bottles = bottleRepository.findByMember(member);

        List<BottleDto.Summary> summaries = new ArrayList<>();

        for (Bottle bottle : bottles) {
            BottleDto.Summary summary = BottleDto.Summary.builder()
                    .bottleId(bottle.getBottleId())
                    .title(bottle.getTitle())
                    .bottleColor(bottle.getBottleColor())
                    .bottleDesign(bottle.getBottleDesign())
                    .view(bottle.getView())
                    .build();
            summaries.add(summary);
        }
        return summaries;
    }

    public BottleDto.Detail showBottle(Long bottleId) {
        Bottle bottle = bottleRepository.findById(bottleId)
                .orElseThrow(IllegalArgumentException::new);

        return BottleDto.Detail.builder()
                .title(bottle.getTitle())
                .dDay(bottle.getDDay())
                .bottleDesign(bottle.getBottleDesign())
                .bottleColor(bottle.getBottleColor())
                .showOrNot(bottle.getShowOrNot())
                .view(bottle.getView())
                .paperCraneCnt(bottle.getPaperCraneCnt())
                .build();
    }
}
