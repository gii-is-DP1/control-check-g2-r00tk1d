package org.springframework.samples.petclinic.feeding;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FeedingRepository extends CrudRepository<Feeding, Integer> {
    List<Feeding> findAll();

    @Query("SELECT u FROM FeedingType u")
    List<FeedingType> findAllFeedingTypes();
    Optional<Feeding> findById(int id);

    @Transactional
    Feeding save(Feeding p);

    @Query("SELECT p FROM FeedingType p WHERE p.name = ?1")
    FeedingType findFeedingTypeByName(String name);
}
