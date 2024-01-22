package com.parkeunyoung.haksugodae.web.controller;

import com.parkeunyoung.haksugodae.service.CraneService;
import com.parkeunyoung.haksugodae.web.dto.CraneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CraneController {

    private final CraneService craneService;

    /*
        종이학 생성
     */
    @PostMapping("/crane")
    public String makeCrane(@RequestBody CraneDto.Detail detail) {
        return craneService.makeCrane(detail);
    }

    /*
        누구나 종이학 제목과 디자인, craneId 에 대해 반환
     */
    @GetMapping("/crane")
    public List<CraneDto.Summary> showCraneByAnyone(@RequestBody CraneDto.Id id) {
        return craneService.showCraneByAnyone(id);
    }

    /*
        본인의 유리병 일 경우 종이학 내용을 반환
     */
    @GetMapping("/crane/{bottleId}")
    public List<CraneDto.Detail> showCraneByOwner(@PathVariable Long bottleId, Authentication auth) {
        return craneService.showCraneByOwner(bottleId, auth.getName());
    }
}
