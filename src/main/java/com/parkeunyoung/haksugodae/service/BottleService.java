package com.parkeunyoung.haksugodae.service;


import com.parkeunyoung.haksugodae.domain.bottle.Bottle;
import com.parkeunyoung.haksugodae.domain.bottle.BottleRepository;
import com.parkeunyoung.haksugodae.domain.crane.Crane;
import com.parkeunyoung.haksugodae.domain.crane.CraneRepository;
import com.parkeunyoung.haksugodae.domain.member.Member;
import com.parkeunyoung.haksugodae.domain.member.MemberRepository;
import com.parkeunyoung.haksugodae.web.dto.BottleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BottleService {

    private final BottleRepository bottleRepository;
    private final MemberRepository memberRepository;
    private final CraneRepository craneRepository;

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

    @Transactional
    public String deleteBottle(BottleDto.Id id, String name) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        List<Bottle> bottles = bottleRepository.findByMember(member);

        for (Bottle bottle : bottles) {
            if (bottle.getBottleId() == id.getBottleId()) {
                List<Crane> cranes = craneRepository.findByBottle(bottle);
                for (Crane crane : cranes) {
                    craneRepository.delete(crane);
                }
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
            if (bottle.getLastCraneIdList().isEmpty()) {
                bottle.updateView(false);
            }
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
        Optional<Bottle> optionalBottle = bottleRepository.findById(bottleId);
        Bottle bottle;

        if (!optionalBottle.isPresent()) {
            return null;
        } else {
            bottle = optionalBottle.get();
            if (bottle.getLastCraneIdList().isEmpty()) {
                bottle.updateView(false);
            }
        }

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
