package com.parkeunyoung.haksugodae.domain.member;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {
    void save(Member member);
    Optional<Member> findByName(String name);
}
