package org.example.environement.repository;

import org.example.environement.entity.Specie;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecieRepositoryPaginate extends PagingAndSortingRepository<Specie,Long> {
}
