package com.parkeunyoung.haksugodae.service;

import com.parkeunyoung.haksugodae.domain.bottle.Bottle;
import com.parkeunyoung.haksugodae.domain.bottle.BottleRepository;
import com.parkeunyoung.haksugodae.domain.crane.Crane;
import com.parkeunyoung.haksugodae.domain.crane.CraneRepository;
import com.parkeunyoung.haksugodae.domain.member.Member;
import com.parkeunyoung.haksugodae.domain.member.MemberRepository;
import com.parkeunyoung.haksugodae.web.dto.CraneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CraneService {

    private final CraneRepository craneRepository;
    private final BottleRepository bottleRepository;
    private final MemberRepository memberRepository;

    public String makeCrane(CraneDto.Detail detail) {
        Bottle bottle = bottleRepository.findById(detail.getBottleId())
                .orElseThrow(IllegalArgumentException::new);
        craneRepository.save(
                Crane.builder()
                        .title(detail.getTitle())
                        .content(detail.getContent())
                        .craneDesign(detail.getCraneDesign())
                        .craneColor(detail.getCraneColor())
                        .view(false)
                        .bottle(bottle)
                        .build()
        );
        return "생성";
    }

    public List<CraneDto.Summary> showCraneByAnyone(CraneDto.Id id) {
        Bottle bottle = bottleRepository.findById(id.getBottleId())
                .orElseThrow(IllegalArgumentException::new);
        List<Crane> cranes = craneRepository.findByBottle(bottle);

        List<CraneDto.Summary> summaries = new ArrayList<>();

        for (Crane crane : cranes) {
            summaries.add(
                    CraneDto.Summary.builder()
                            .craneId(crane.getCraneId())
                            .title(crane.getTitle())
                            .view(crane.getView())
                            .build()
            );
        }

        return summaries;
    }
    @Transactional
    public List<CraneDto.Detail> showCraneByOwner(Long bottleId, String name) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);

        // 요청 받은 유리병
        Bottle requestBottle = bottleRepository.findById(bottleId)
                .orElseThrow(IllegalArgumentException::new);

        // 본인의 유리병 리스트
        List<Bottle> bottles = bottleRepository.findByMember(member);

        List<CraneDto.Detail> details = new ArrayList<>();

        for (Bottle bottle : bottles) {
            if (bottle.getBottleId() == requestBottle.getBottleId()) {
                List<Crane> cranes = craneRepository.findByBottle(requestBottle);

                for (Crane crane : cranes) {
                    details.add(
                            CraneDto.Detail.builder()
                                    .title(crane.getTitle())
                                    .content(crane.getContent())
                                    .craneDesign(crane.getCraneDesign())
                                    .craneColor(crane.getCraneColor())
                                    .bottleId(bottleId)
                                    .build()
                    );
                    crane.updateView(true);
                }
                return details;
            }
        }
        return null;
    }
}
