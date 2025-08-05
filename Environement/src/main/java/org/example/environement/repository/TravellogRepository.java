package org.example.environement.repository;

import org.example.environement.entity.Travellog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellogRepository extends PagingAndSortingRepository<Travellog,Long> {
    public List<Travellog> findTravellogByObservation_Id (long id);

    List<Travellog> findByObservationId(Long observationId);

    Travellog save(Travellog travellog);

//    @Query("select t from Travellog t where t.observation.observerName = ?1 and t.observation.observationDate > ?2")
//    public List<Travellog> findTravellogByUserForLastMonth (String user, LocalDate date);
}
