package com.parkeunyoung.haksugodae.domain.bottle;

import com.parkeunyoung.haksugodae.domain.member.Member;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BottleRepository extends Repository<Bottle, Long> {
    void save(Bottle bottle);
    List<Bottle> findByMember(Member member);
    void delete(Bottle bottle);
}
