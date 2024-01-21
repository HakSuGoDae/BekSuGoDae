package com.parkeunyoung.haksugodae.domain.bottle;

import com.parkeunyoung.haksugodae.domain.member.Member;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface BottleRepository extends Repository<Bottle, Long> {
    void save(Bottle bottle);
    List<Bottle> findByMember(Member member);
    void delete(Bottle bottle);
    Optional<Bottle> findById(Long bottleId);
}
