package com.parkeunyoung.haksugodae.domain.crane;

import com.parkeunyoung.haksugodae.domain.bottle.Bottle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CraneRepository extends Repository<Crane, Long> {
    void save(Crane crane);
    List<Crane> findByBottle(Bottle bottle);
    Page<Crane> findByBottleOrderByCreatedDate(Bottle bottle, Pageable pageable);
    Optional<Crane> findById(Long id);
    void delete(Crane crane);
}
