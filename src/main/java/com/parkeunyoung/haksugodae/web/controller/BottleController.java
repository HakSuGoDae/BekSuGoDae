package com.parkeunyoung.haksugodae.web.controller;

import com.parkeunyoung.haksugodae.service.BottleService;
import com.parkeunyoung.haksugodae.web.dto.BottleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BottleController {

    private final BottleService bottleService;

    /*
        유리병 생성
     */
    @PostMapping("/bottle")
    public String makeBottle(@RequestBody BottleDto.Detail detail, Authentication auth) {
        return bottleService.makeBottle(detail, auth.getName());
    }

    /*
        유리병 삭제
     */
    @DeleteMapping("/bottle")
    public String deleteBottle(@RequestBody BottleDto.Id id, Authentication auth) {
        return bottleService.deleteBottle(id, auth.getName());
    }

    /*
        본인 유리병의 일부 정보 반환
     */
    @GetMapping("/bottle")
    public List<BottleDto.Summary> showBottleByMember(Authentication auth) {
        return bottleService.showBottleByMember(auth.getName());
    }

    /*
        하나의 유리병에 대한 정보 반환
     */
    @GetMapping("/bottle/{bottleId}")
    public BottleDto.Detail showBottle(@PathVariable Long bottleId) {
        return bottleService.showBottle(bottleId);
    }

}
