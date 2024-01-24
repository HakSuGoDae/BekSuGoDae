package com.parkeunyoung.haksugodae.domain.bottle;


import com.parkeunyoung.haksugodae.domain.basetime.BaseTimeEntity;
import com.parkeunyoung.haksugodae.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Bottle extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bottleId;
    private String title;
    private Date dDay;
    private String bottleDesign;
    private String bottleColor;
    private Boolean showOrNot;
    private Long paperCraneCnt;
    private Boolean view;
    @ManyToOne
    private Member member;
    public void updateView(Boolean view) {
        this.view = view;
    }
    public void increaseCraneCnt() {
        this.paperCraneCnt++;
    }
}
