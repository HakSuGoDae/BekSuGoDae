package com.parkeunyoung.haksugodae.web.controller;

import com.parkeunyoung.haksugodae.domain.bottle.Bottle;
import com.parkeunyoung.haksugodae.service.BottleService;
import com.parkeunyoung.haksugodae.web.dto.BottleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
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
    public ResponseEntity makeBottle(@RequestBody BottleDto.Detail detail, Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        bottleService.makeBottle(detail, auth.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
        유리병 삭제
     */
    @DeleteMapping("/bottle")
    public ResponseEntity deleteBottle(@RequestBody BottleDto.Id id, Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String s = bottleService.deleteBottle(id, auth.getName());

        if (!s.equals("삭제")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
        본인 유리병의 일부 정보 반환
     */
    @GetMapping("/bottle")
    public ResponseEntity<List<BottleDto.Summary>> showBottleByMember(Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<BottleDto.Summary> summaries = bottleService.showBottleByMember(auth.getName());
        return ResponseEntity.status(HttpStatus.OK).body(summaries);
    }

    /*
        하나의 유리병에 대한 정보 반환
     */
    @GetMapping("/bottle/{bottleId}")
    public ResponseEntity<BottleDto.Detail> showBottle(@PathVariable Long bottleId) {
        BottleDto.Detail detail = bottleService.showBottle(bottleId);

        if (detail == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(detail);
    }

}
