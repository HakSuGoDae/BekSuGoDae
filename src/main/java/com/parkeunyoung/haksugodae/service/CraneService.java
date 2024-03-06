package com.parkeunyoung.haksugodae.service;

import com.parkeunyoung.haksugodae.domain.bottle.Bottle;
import com.parkeunyoung.haksugodae.domain.bottle.BottleRepository;
import com.parkeunyoung.haksugodae.domain.crane.Crane;
import com.parkeunyoung.haksugodae.domain.crane.CraneRepository;
import com.parkeunyoung.haksugodae.domain.member.Member;
import com.parkeunyoung.haksugodae.domain.member.MemberRepository;
import com.parkeunyoung.haksugodae.web.dto.CraneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CraneService {

    private final CraneRepository craneRepository;
    private final BottleRepository bottleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String makeCrane(CraneDto.Detail detail) {
        Bottle bottle = bottleRepository.findById(detail.getBottleId())
                .orElseThrow(IllegalArgumentException::new);
        Crane crane = Crane.builder()
                .title(detail.getTitle())
                .content(detail.getContent())
                .craneDesign(detail.getCraneDesign())
                .craneColor(detail.getCraneColor())
                .view(true)
                .bottle(bottle)
                .build();
        craneRepository.save(crane);

        bottle.addLastCraneId(crane.getCraneId());
        bottle.increaseCraneCnt();
        bottle.updateView(true);
        return "생성";
    }

    @Transactional
    public CraneDto.Detail showCrane(Long craneId, String name) {
        Optional<Crane> optionalCrane = craneRepository.findById(craneId);
        if (!optionalCrane.isPresent()) {
            return null;
        }
        Crane crane = optionalCrane.get();
        String bottleOwner = crane.getBottle().getMember().getName();
        if (bottleOwner.equals(name)) {
            crane.updateView(false);
            crane.getBottle().removeLastCraneId(craneId);
            return CraneDto.Detail.builder()
                    .title(crane.getTitle())
                    .content(crane.getContent())
                    .craneDesign(crane.getCraneDesign())
                    .craneColor(crane.getCraneColor())
                    .bottleId(crane.getBottle().getBottleId())
                    .view(crane.getView())
                    .build();
        }
        return null;
    }

    public List<CraneDto.Summary> showCraneByAnyone(Long bottleId, Pageable pageable) {
        Optional<Bottle> optionalBottle = bottleRepository.findById(bottleId);
        Bottle bottle;

        if (!optionalBottle.isPresent()) {
            return null;
        } else {
            bottle = optionalBottle.get();
        }

        Page<Crane> pages = craneRepository.findByBottleOrderByCreatedDate(bottle, pageable);

        List<CraneDto.Summary> summaries = new ArrayList<>();

        for (Crane crane : pages.getContent()) {
            summaries.add(
                    CraneDto.Summary.builder()
                            .craneId(crane.getCraneId())
                            .title(crane.getTitle())
                            .craneColor(crane.getCraneColor())
                            .craneDesign(crane.getCraneDesign())
                            .view(crane.getView())
                            .build()
            );
        }

        return summaries;
    }
}
