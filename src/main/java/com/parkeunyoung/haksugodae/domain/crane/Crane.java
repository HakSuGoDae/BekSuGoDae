package com.parkeunyoung.haksugodae.domain.crane;

import com.parkeunyoung.haksugodae.domain.basetime.BaseTimeEntity;
import com.parkeunyoung.haksugodae.domain.bottle.Bottle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crane extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long craneId;

    private String title;
    private String content;
    private String craneDesign;
    private String craneColor;
    private Boolean view;
    @ManyToOne(fetch = FetchType.LAZY)
    private Bottle bottle;

    public void updateView(Boolean view) {
        this.view = view;
    }
}
