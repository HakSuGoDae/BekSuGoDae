package com.parkeunyoung.haksugodae.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CraneDto {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Detail {
        private String title;
        private String content;
        private String craneDesign;
        private String craneColor;
        private Long bottleId;
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
        private Long craneId;
        private String title;
        private String craneDesign;
        private String craneColor;
    }
}
