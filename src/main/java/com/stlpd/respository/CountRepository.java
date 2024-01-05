package com.stlpd.respository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stlpd.model.Call;
import com.stlpd.model.Count;

@Repository
public interface CountRepository extends JpaRepository<Call, Long> {

    List<Count> findByLocationAndDatetimeBetween(String location, LocalDateTime startDate, LocalDateTime endDate);

    List<Count> findByTypeAndDatetimeBetween(String type, LocalDateTime startDate, LocalDateTime endDate);

}
