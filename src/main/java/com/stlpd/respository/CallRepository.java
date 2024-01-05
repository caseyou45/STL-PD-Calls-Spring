package com.stlpd.respository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stlpd.model.Call;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {

    Optional<Call> findByEventID(String callIDString);

    List<Call> findByDatetimeAfterOrderByDatetimeDesc(LocalDateTime daysAgo);

}
