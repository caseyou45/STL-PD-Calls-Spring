package com.stlpd.respository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stlpd.model.Call;
import com.stlpd.model.Incident;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {

        Optional<Call> findByEventID(String callIDString);

        List<Call> findByDatetimeBetweenOrderByDatetimeDesc(LocalDateTime startDate,
                        LocalDateTime endDate);

        List<Call> findByLocationAndDatetimeBetweenOrderByDatetimeDesc(String location, LocalDateTime startDate,
                        LocalDateTime endDate);

        List<Call> findByTypeAndDatetimeBetweenOrderByDatetimeDesc(String type, LocalDateTime startDate,
                        LocalDateTime endDate);

        List<Call> findByTypeAndLocationAndDatetimeBetweenOrderByDatetimeDesc(String type, String location,
                        LocalDateTime startDate, LocalDateTime endDate);

}
