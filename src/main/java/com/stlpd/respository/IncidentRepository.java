package com.stlpd.respository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stlpd.model.Incident;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    Optional<Incident> findByInciId(String inciIdString);

    List<Incident> findByConvertedDateBetweenOrderByConvertedDateDesc(LocalDateTime startDate, LocalDateTime endDate);

    Optional<Incident> findFirstByIncidentLocationLike(String location);

}
