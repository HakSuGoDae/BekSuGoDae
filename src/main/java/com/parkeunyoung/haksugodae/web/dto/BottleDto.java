package com.parkeunyoung.haksugodae.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


public class BottleDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Detail {
        private String title;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date dDay;
        private String bottleDesign;
        private String bottleColor;
        private Boolean showOrNot;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Id {
        private Long bottleId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Summary {
        private Long bottleId;
        private String title;
        private String bottleDesign;
        private String bottleColor;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SummaryList {
        private List<Summary> bottles;
    }
}
