package com.stlpd.respository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.stlpd.model.Call;

@Repository
public interface CountRepository extends JpaRepository<Call, Long> {
        @Query("SELECT SUM(c.count) FROM Count c WHERE c.location = :location AND c.datetime BETWEEN :startDate AND :endDate")
        Integer findSumByLocationAndDatetimeBetween(@Param("location") String location,
                        @Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);

        @Query("SELECT SUM(c.count) FROM Count c WHERE c.type = :type AND c.datetime BETWEEN :startDate AND :endDate")
        Integer findSumByTypeAndDatetimeBetween(@Param("type") String type,
                        @Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);
}
