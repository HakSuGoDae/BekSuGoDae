package com.parkeunyoung.haksugodae.domain.member;

import org.springframework.data.repository.Repository;

public interface MemberRepository extends Repository<Member, Long> {
    void save(Member member);
}
