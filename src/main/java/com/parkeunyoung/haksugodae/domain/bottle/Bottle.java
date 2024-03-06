package com.parkeunyoung.haksugodae.domain.bottle;


import com.parkeunyoung.haksugodae.domain.basetime.BaseTimeEntity;
import com.parkeunyoung.haksugodae.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @ElementCollection
    @CollectionTable(name = "LAST_CRANE_ID",
                    joinColumns = @JoinColumn(name = "BOTTLE_ID"))
    private List<Long> lastCraneIdList;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    public void updateView(Boolean view) {
        this.view = view;
    }
    public void increaseCraneCnt() {
        this.paperCraneCnt++;
    }

    public void addLastCraneId(Long id) {
        this.lastCraneIdList.add(id);
    }

    public void removeLastCraneId(Long id) {
        this.lastCraneIdList.remove(id);
    }
}
