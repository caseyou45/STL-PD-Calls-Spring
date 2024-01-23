package com.stlpd.respository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.stlpd.model.Call;

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

        @Query("SELECT c FROM Call c JOIN c.locationEntity l " +
                        "WHERE l.neighborhood = :neighborhood " +
                        "AND c.datetime BETWEEN :startDate AND :endDate " +
                        "ORDER BY c.datetime DESC")
        List<Call> findByLocationNeighborhoodAndDatetimeBetweenOrderByDatetimeDesc(String neighborhood,
                        LocalDateTime startDate,
                        LocalDateTime endDate);

        @Query("SELECT c FROM Call c WHERE " +
                        "(:id IS NULL OR c.id = :id) AND " +
                        "(:type IS NULL OR c.type = :type) AND " +
                        "(:location IS NULL OR c.location = :location) AND " +
                        "(:neighborhood IS NULL OR c.locationEntity.neighborhood = :neighborhood) AND " +
                        "(c.datetime BETWEEN :startDate AND :endDate) " +
                        "ORDER BY c.datetime DESC")
        List<Call> findByDynamicParameters(Long id, String type, String location, String neighborhood,
                        LocalDateTime startDate, LocalDateTime endDate);

}
