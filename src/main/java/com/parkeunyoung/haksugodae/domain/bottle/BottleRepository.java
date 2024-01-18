package com.parkeunyoung.haksugodae.domain.bottle;

import com.parkeunyoung.haksugodae.domain.member.Member;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface BottleRepository extends Repository<Bottle, Long> {
    void save(Member member);
    Optional<Member> findByMember(Member member);
}
