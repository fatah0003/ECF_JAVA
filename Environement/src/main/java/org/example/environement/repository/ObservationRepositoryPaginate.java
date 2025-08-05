package org.example.environement.repository;

import org.example.environement.entity.Observation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationRepositoryPaginate extends PagingAndSortingRepository<Observation, Long> {
}
