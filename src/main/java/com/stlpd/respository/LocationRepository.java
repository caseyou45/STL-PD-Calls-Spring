package com.stlpd.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stlpd.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByLocationApprox(String locationApprox);

    Optional<Location> findFirstByLocationApprox(String locationApprox);

}
