package com.stlpd.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stlpd.model.Neighborhood;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {

    Optional<Neighborhood> findByNumber(String number);

    Optional<Neighborhood> findByNeighborhood(String neighborhood);

}
