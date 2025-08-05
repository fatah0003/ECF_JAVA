package org.example.environement.repository;

import org.example.environement.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {

    List<Observation> findObservationByLocationIsLike(String location);

    List<Observation> findObservationBySpecieId(long specieId);
}
