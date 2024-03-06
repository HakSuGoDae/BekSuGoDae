package com.parkeunyoung.haksugodae.web.controller;

import com.parkeunyoung.haksugodae.service.CraneService;
import com.parkeunyoung.haksugodae.web.dto.CraneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity makeCrane(@RequestBody CraneDto.Detail detail) {
        craneService.makeCrane(detail);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
        종이학 하나씩 보여 주기
     */
    @GetMapping("/crane/{craneId}")
    public ResponseEntity<CraneDto.Detail> showCraneOne(@PathVariable Long craneId, Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (craneService.showCrane(craneId, auth.getName()) == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(craneService.showCrane(craneId, auth.getName()));
    }

    /*
        누구나 종이학 제목과 디자인, craneId 에 대해 반환
     */
    @GetMapping("/crane/{bottleId}/anyone")
    public ResponseEntity<List<CraneDto.Summary>> showCraneByAnyone(@PathVariable Long bottleId, Pageable pageable) {
        List<CraneDto.Summary> summaries = craneService.showCraneByAnyone(bottleId, pageable);

        if (summaries == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(summaries);
    }
}
